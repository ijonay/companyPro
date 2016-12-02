package com.zc.service;

import com.zc.WordRedisModel;
import com.zc.bean.TempWordAttr;
import com.zc.bean.Topic;
import com.zc.bean.Weibo;
import com.zc.dao.TempWordAttrMapper;
import com.zc.dao.WeiboDao;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.path.*;
import com.zc.model.path.PathModel;
import com.zc.model.weibo.WeiboItemModel;
import com.zc.utility.*;
import com.zc.utility.exception.ServiceException;
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
import java.util.stream.Collectors;

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

    @Autowired
    private TempWordAttrMapper tempWordAttrMapper;


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

    @Override
    public HashMap<String, Object> getPathSearch(String start, String end, Integer frequency) {

        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        EffectLog effectLog = new EffectLog("getPathSearch");


        float[] startVectors = wordService.getWordVectorsByCache(start);
        float[] endVectors = wordService.getWordVectorsByCache(end);

        effectLog.add("loadVectors");

        if (Objects.isNull(startVectors)) {

            throw new ServiceException(StatusCodeEnum.FAILED, "当前节点值无效！");

        }
        if (Objects.isNull(endVectors)) {

            throw new ServiceException(StatusCodeEnum.FAILED, "输入目标值无效！");
        }

        Map<String, float[]> modelMap = wordService.getModelMap();

        Map<String, ValModel> mapResult = new HashMap<>();

        modelMap.forEach((k, v) -> mapResult.put(k, new ValModel(v, WordVectorHelper.getSimilarity(startVectors,
                v))));

        effectLog.add("getSimilarity");

        List<Map.Entry<String, ValModel>> list = new ArrayList<>(mapResult.entrySet());

        Collections.sort(list, (o1, o2) ->
                CommonHelper.compare(o2.getValue().getSimilarity(),
                        o1.getValue().getSimilarity())
        );
        effectLog.add("sort by similarity");

        Map.Entry<String, ValModel> endItem = list.stream().filter(p -> p.getKey().equals(end)).findFirst().get();

        effectLog.add("get endItem similarity");


//        List<MapModel<String, Object>> result = new ArrayList<>();

        LinkedHashMap<String, HashMap<String, Object>> result1 = new LinkedHashMap<>();

        HashMap<String, Object> resultMap = new HashMap<>();


        if (Objects.nonNull(endItem)) {

            for (int i = 1; i < list.size(); i++) {

                Map.Entry<String, ValModel> item = list.get(i);

                if (item.getValue().getSimilarity() >= endItem.getValue().getSimilarity() && item.getValue()
                        .getSimilarity
                                () > 0) {

                    HashMap<String, Object> r = new HashMap<>();

                    r.put("similarity", item.getValue().getSimilarity());
                    r.put("key", item.getKey());

                    result1.put(item.getKey(), r);

                } else {
                    break;
                }

                if (item.getKey().equals(end)) break;

            }

            effectLog.add("get last result");

        }

        List<HashMap<String, Object>> result = new ArrayList<>();


        if (result1.size() > 0) {

            Set<String> keys = result1.keySet();

            List<TempWordAttr> words = tempWordAttrMapper.getCollByWords(keys, frequency);

            Map<String, TempWordAttr> collect = new HashMap<>();

            for (TempWordAttr item : words) {

                if (!collect.containsKey(item.getName()))
                    collect.put(item.getName(), item);

            }

            keys.retainAll(collect.keySet());

            for (String key : keys) {

                HashMap<String, Object> item = result1.get(key);
                TempWordAttr tempWordAttr = collect.get(key);
                item.put("attr", tempWordAttr.getAttr());
                item.put("num", tempWordAttr.getNum());

                result.add(item);

            }


        }

        resultMap.put("vals", result.stream().limit(3000).collect(Collectors.toList()));

        return resultMap;
    }

    //region helper method
    private List<PathModel> getAllPath(String start, float[] targetVector) {


        this.targetVector = targetVector;
        isFirst = true;


        log.add(start);

        generatePath(new PathNode(start, null, 0));
        log.add(start + "1");
        List<PathModel> result = new ArrayList<>();
        this.pathList.forEach(p -> {


            Set<Edge> edges = new HashSet<>();
            LinkedList<Node> nodes = new LinkedList<>();
            PathModel model = new PathModel(nodes, edges);

            p.forEach(n -> {
                if (!StringUtils.isEmpty(n.getPrevName())) {
                    edges.add(new Edge(n.getPrevName(), n.getName(), n.getSimilarity()));
                }

                nodes.add(new Node(n.getName()));
            });

            if (edges.size() < 1) return;

            result.add(model);

        });

        return result;
    }

    private void generatePath(PathNode start) {
        path.push(start);
        onPath.add(start.getName());

        log.add("generatePath_" + start.getName());

        if (path.size() <= MAX_PATHLENGTH) {
            if (isSatisfied(start.getName(), targetVector)) {
                LinkedList<PathNode> tempPath = new LinkedList<>();
                tempPath.addAll(path);
                this.pathList.add(tempPath);
                if (isFirst) {
                    isFirst = false;
                    runRecursion(start.getName(), this.targetVector);
                }
            } else {
                runRecursion(start.getName(), this.targetVector);
            }
        }
        log.add("generatePath_end");
        path.pop();
        onPath.remove(start.getName());
    }

    private void runRecursion(String start, float[] targetVector) {
        log.add("runRecursion_" + start);
        Set<WordRedisModel> neighbors = redisTemplate.boundZSetOps(PropertyHelper
                .getValue(Constant.CONFIG_PROPERTIES, Constant.WORDR_EDISKEY_PREFIX_KEY) + start).range(0, TOPNSIZE -
                1);
        log.add("runRecursion_" + start + "_1");
        if (neighbors != null) {
            LinkedList<WordRedisModel> tempNeighbors = getSortedWordEntryList(neighbors, targetVector);
            for (WordRedisModel w : tempNeighbors) {
                if (!StringUtils.isEmpty(w.name) && !isDisSimilarity(start, w.name) && !onPath.contains(w
                        .name)) {
                    float similarity = getSimilarity(start, w.getName());
                    generatePath(new PathNode(w.name, start, similarity));
                }
            }
        }
        log.add("runRecursion_" + start + "_end");
        log.writeToConsole();
    }

    private boolean isDisSimilarity(String start, String target) {
        return getSimilarity(start, target) <= DISSIMILARITY_THRESHOLD;
    }

    private boolean isSatisfied(String start, float[] targetVector) {
        if (getSimilarity(start, targetVector) > 2) {
            System.out.println("zhang");
        }
        return getSimilarity(start, targetVector) >= SIMILARITY_THRESHOLD;
    }

    private float getSimilarity(String start, String target) {
        float[] tVector = wordService.getModelMap().get(target);
        return getSimilarity(start, tVector);
    }

    private float getSimilarity(String start, float[] targetVector) {
        float[] startVector = wordService.getModelMap().get(start);
        float similarity = WordVectorHelper.getSimilarity(startVector, targetVector);
        return similarity;
    }

    private LinkedList<WordRedisModel> getSortedWordEntryList(Set<WordRedisModel> neighbors, float[] targetVector) {
        LinkedList<WordRedisModel> list = new LinkedList(neighbors);
        String key = Math.random() + "";
        log.add("getSortedWordEntryList_" + key + "_1");
        Collections.sort(list, (left, right) -> CommonHelper.compare(
                WordVectorHelper.getSimilarity(wordService.getModelMap().get(right.name), targetVector),
                WordVectorHelper.getSimilarity(wordService.getModelMap().get(left.name), targetVector)
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


}
