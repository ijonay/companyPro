package com.zc.service;

import com.zc.WordRedisModel;
import com.zc.bean.Topic;
import com.zc.bean.Weibo;
import com.zc.dao.WeiboDao;
import com.zc.model.path.*;
import com.zc.model.weibo.WeiboItemModel;
import com.zc.utility.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 张镇强 on 2016/8/16 16:02.
 */
@Service
public class PathServiceImpl implements PathService {
    //region autowired
    @Autowired
    private TopicService topicService;
    @Resource
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Autowired
    private WordService wordService;
    @Autowired
    private WeiboDao weiboDao;

    @Autowired
    private ZCRedisService<float[]> redisService;


    //endregion

    //region fields
    private final float SIMILARITY_THRESHOLD =
            Float.parseFloat(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.SIMILARITY_THRESHOLD));
    private final int TOPNSIZE =
            Integer.parseInt(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.TOP_NSIZE));
    private final float DISSIMILARITY_THRESHOLD =
            Float.parseFloat(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.DISSIMILARITY_THRESHOLD));
    // 不相似阈值
    private int MAX_PATHLENGTH = Integer.parseInt(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant
            .MAX_PATHLENGTH));

    private List<LinkedList<PathNode>> pathList;
    private boolean isFirst = true;
    private Stack<PathNode> path = new Stack<>();
    private Set<String> onPath = new HashSet<>();
    private float[] targetVector;
    //endregion
    EffectLog log = new EffectLog("PathServiceImpl");

    public List<PathModel> getPaths(Integer topicId, String query) {
        this.pathList = new ArrayList<>();
        path = new Stack<>();
        onPath = new HashSet<>();
        log = new EffectLog("PathServiceImpl");

        log.add("1");

        Topic topic = topicService.get(topicId);
        float[] topicVector = CommonHelper.stringToFloatArray(topic.getCoordinate());


        List<PathModel> paths = getAllPath(query, topicVector);

        log.add("writeToConsole");

        log.writeToConsole();

        return paths;
    }

    @Override
    public NodeRelations getRelations(String startNode, String endNode) {
        NodeRelations result = new NodeRelations();
        String url = PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.SOLR_URL);
        HttpSolrServer server = new HttpSolrServer(url);
        server.setRequestWriter(new BinaryRequestWriter());
        SolrQuery query = new SolrQuery();
        query.setQuery(String.format("weibo_content:\"%s\" OR weibo_content:\"%s\"", startNode, endNode))
//                .addFilterQuery(String.format("weibo_content:\"%s\"", endNode))
                .setStart(0)
                .setRows(Integer.valueOf(PropertyHelper
                        .getValue(Constant.CONFIG_PROPERTIES, Constant.SOLR_SEARCH_LENGTH_KEY)));
        try {
            QueryResponse response = server.query(query);
            System.out.println(response.getResults().getNumFound());
            List<WeiboItemModel> weiboItemModels = new ArrayList<>();
            for (SolrDocument r : response.getResults()) {
                WeiboItemModel weiboItemModel = new WeiboItemModel();
                weiboItemModel.setId(Integer.parseInt(r.getFieldValue("id").toString()));
                weiboItemModel.setWeiboContent(r.getFieldValue("weibo_content").toString());
                weiboItemModels.add(weiboItemModel);
            }
            result.setWeiboItemModels(weiboItemModels);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return result;

//        List<Weibo> weiboList = weiboDao.getAll();
//
//        long start = System.currentTimeMillis();
//
//        Collection<Weibo> relatedWeibos = getRelatedWeibo(startNode, endNode, weiboList);
//        NodeRelations nodeRelations = new NodeRelations();
//        nodeRelations.setWeiboItemModels(new WeiboCollection(relatedWeibos));
//
//        long span = System.currentTimeMillis() - start;
//        System.out.println(span);
//        return nodeRelations;
    }

    //region helper method
    private List<PathModel> getAllPath(String start, float[] targetVector) {


        Map<String, float[]> allVector = getWordVectorsCollByCache();

        this.targetVector = targetVector;
        isFirst = true;


        log.add(start);

        generatePath(new PathNode(start, null, 0), allVector);
        log.add(start + "1");
        List<PathModel> result = new ArrayList<>();
        this.pathList.forEach(p -> {
            Set<Edge> edges = new HashSet<>();
            LinkedList<Node> nodes = new LinkedList<>();
            PathModel model = new PathModel(nodes, edges);
            result.add(model);
            p.forEach(n -> {
                if (!StringUtils.isEmpty(n.getPrevName())) {
                    edges.add(new Edge(n.getPrevName(), n.getName(), n.getSimilarity()));
                }

                nodes.add(new Node(n.getName()));
            });

        });

        return result;
    }

    private void generatePath(PathNode start, Map<String, float[]> allVector) {
        path.push(start);
        onPath.add(start.getName());

        log.add("generatePath_" + start.getName());

        if (path.size() <= MAX_PATHLENGTH) {
            if (isSatisfied(start.getName(), targetVector, allVector)) {
                LinkedList<PathNode> tempPath = new LinkedList<>();
                tempPath.addAll(path);
                this.pathList.add(tempPath);
                if (isFirst) {
                    isFirst = false;
                    runRecursion(start.getName(), this.targetVector, allVector);
                }
            } else {
                runRecursion(start.getName(), this.targetVector, allVector);
            }
        }
        log.add("generatePath_end");
        path.pop();
        onPath.remove(start.getName());
    }

    private void runRecursion(String start, float[] targetVector, Map<String, float[]> allVector) {
        log.add("runRecursion_" + start);
        Set<WordRedisModel> neighbors = redisTemplate.boundZSetOps(PropertyHelper
                .getValue(Constant.CONFIG_PROPERTIES, Constant.WORDR_EDISKEY_PREFIX_KEY) + start).range(0, TOPNSIZE -
                1);
        log.add("runRecursion_" + start + "_1");
        if (neighbors != null) {
            LinkedList<WordRedisModel> tempNeighbors = getSortedWordEntryList(neighbors, targetVector, allVector);
            for (WordRedisModel w : tempNeighbors) {
                if (!StringUtils.isEmpty(w.name) && !isDisSimilarity(start, w.name, allVector) && !onPath.contains(w
                        .name)) {
                    float similarity = getSimilarity(start, w.getName(), allVector);
                    generatePath(new PathNode(w.name, start, similarity), allVector);
                }
            }
        }
        log.add("runRecursion_" + start + "_end");
        log.writeToConsole();
    }

    private boolean isDisSimilarity(String start, String target, Map<String, float[]> allVector) {
        return getSimilarity(start, target, allVector) <= DISSIMILARITY_THRESHOLD;
    }

    private boolean isSatisfied(String start, float[] targetVector, Map<String, float[]> allVector) {
        if (getSimilarity(start, targetVector, allVector) > 2) {
            System.out.println("zhang");
        }
        return getSimilarity(start, targetVector, allVector) >= SIMILARITY_THRESHOLD;
    }

    private float getSimilarity(String start, String target, Map<String, float[]> allVector) {
        float[] tVector = allVector.get(target);
        return getSimilarity(start, tVector, allVector);
    }

    private float getSimilarity(String start, float[] targetVector, Map<String, float[]> allVector) {
        float[] startVector = allVector.get(start);
        float similarity = WordVectorHelper.getSimilarity(startVector, targetVector);
        return similarity;
    }

    private LinkedList<WordRedisModel> getSortedWordEntryList(Set<WordRedisModel> neighbors, float[] targetVector,
                                                              Map<String, float[]> allVector) {
        LinkedList<WordRedisModel> list = new LinkedList(neighbors);
        String key = Math.random() + "";
        log.add("getSortedWordEntryList_" + key + "_1");
        Collections.sort(list, (left, right) -> CommonHelper.compare(
                WordVectorHelper.getSimilarity(allVector.get(right.name), targetVector),
                WordVectorHelper.getSimilarity(allVector.get(left.name), targetVector)
                )
        );
        log.add("getSortedWordEntryList_" + key + "_1");
        log.writeToConsole();
        return list;
    }

    private Collection<Weibo> getRelatedWeibo(String startNode, String endNode, List<Weibo> weiboList) {
        int resultSize = 3;
        SortedMap<Float, Weibo> resultWeibos = new TreeMap<>();
        float[] sVector = wordService.getWordVectorsByCache(startNode);
        float[] eVector = wordService.getWordVectorsByCache(endNode);
        for (Weibo w : weiboList) {
            float[] weiboVector = CommonHelper.stringToFloatArray(w.getCoordinate());
            float sSimilarity = WordVectorHelper.getSimilarity(sVector, weiboVector);
            float eSimilarity = WordVectorHelper.getSimilarity(eVector, weiboVector);
            float weiboSimilarity = sSimilarity + eSimilarity;
            if (resultWeibos.size() <= 3) {
                resultWeibos.put(weiboSimilarity, w);
                continue;
            }

            if (resultWeibos.firstKey() >= weiboSimilarity) {
                continue;
            }

            if (resultWeibos.size() >= resultSize) {
                resultWeibos.remove(resultWeibos.firstKey());
            }

            resultWeibos.put(weiboSimilarity, w);
        }

        return resultWeibos.values();
    }
    //endregion

    @Override
    public Map<String, float[]> getWordVectorsCollByCache() {

        String keyPrefix = PropertyHelper.getValue(Constant
                        .CONFIG_PROPERTIES,
                Constant.WORD_VECTORS_KEY);

        return redisService.getCacheObject(keyPrefix);

    }
}
