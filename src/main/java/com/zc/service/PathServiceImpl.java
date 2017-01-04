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

        List<PathModel> paths = getAllPathZhiHu(topic, query);

        //List<PathModel> paths = getAllPath(query, topicVector);

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

        if (isSatisfied(start.getName(), targetVector)) {
            LinkedList<PathNode> tempPath = new LinkedList<>();
            tempPath.addAll(path);
            this.pathList.add(tempPath);
            if (isFirst) {
                isFirst = false;
                if( path.size() < MAX_PATHLENGTH ){
                    runRecursion(start.getName(), this.targetVector);
                }

            }
        } else {
            if ( path.size() < MAX_PATHLENGTH ) {
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

    //this function is similar to funcation getAllPath
    private List<PathModel> getAllPathZhiHu(Topic topic,  String keyword){

        String topicKeywordStr = topic.getKeywords();
        String[] tkwArray = topicKeywordStr.split(",");
        List<String> tkwList = new ArrayList<String>(Arrays.asList(tkwArray));
        tkwList = tkwList.stream().map(String :: trim).collect(Collectors.toList());

        List<PathModel> pathModelList = new ArrayList<>();
        List<String> childrenTopicNameList = Collections.emptyList();
        Set<String> tempUniqueSet = new HashSet<String>();
        List<String> neighborWordsList = topicService.getTopicNeighborWords(topic, 20);
        List<String> titleWordsList = topicService.getTopicTitleKeywords(topic.getId());
        tkwList.addAll(neighborWordsList);
        tkwList.addAll(titleWordsList);
        tempUniqueSet.addAll(tkwList);
        List<String> topicKeywordList  = new ArrayList<String>(tempUniqueSet);
        try{
            List<String> zhiHuTopicsList = HttpClientHelper.searchZhiHuTopics(keyword);
            if( !zhiHuTopicsList.isEmpty() ){// there is a sub topic ot the keyword at least

                String theFirstTopic = zhiHuTopicsList.get(0);
                if( StringUtils.equals(theFirstTopic,keyword) ){ // the keyword equals the first topic
                    childrenTopicNameList = topicService.getChildrenTopicNames(theFirstTopic);
                    if( childrenTopicNameList.isEmpty() ){  // there is no sub topics of the theFirstTopic
                        // keyword -> topic keywords -> title
                        //matching the topic keywords directly
                        childrenTopicNameList.add(keyword);

                        // repeated words list
                        List<String> repeatedWordList = topicService.getRepeatedWordList(topicKeywordList, childrenTopicNameList);
                        updateRepeatedWordPathModelList(keyword, repeatedWordList, pathModelList);

                        // similar words list
                        List<String> similarWordList = topicService.getSimilarWords(topicKeywordList, childrenTopicNameList);
                        updateSimilarWordPathModelList(similarWordList, pathModelList);

                    }else{  // there are sub topics of the theFirstTopic

                        if(!childrenTopicNameList.contains(keyword)){
                            childrenTopicNameList.add(keyword);
                        }
                        // repeated words list
                        List<String> repeatedWordList = topicService.getRepeatedWordList(topicKeywordList, childrenTopicNameList);
                        updateRepeatedWordPathModelList(keyword, repeatedWordList, pathModelList);

                        // similar words list
                        List<String> similarWordList = topicService.getSimilarWords(topicKeywordList, childrenTopicNameList);
                        updateSimilarWordPathModelList3(keyword, similarWordList, pathModelList);

                    }

                }else{ // the keyword doesn't equals the first topic
                    // the first node is the keyword
                    childrenTopicNameList = topicService.getChildrenTopicNames(theFirstTopic);
                    if( childrenTopicNameList.isEmpty() ){  // match the theFirstTopic with topic keywords
                        childrenTopicNameList.add( theFirstTopic );
                        childrenTopicNameList.add( keyword );

                        // repeated words list
                        List<String> repeatedWordList = topicService.getRepeatedWordList(topicKeywordList, childrenTopicNameList);
                        updateRepeatedWordPathModelList(keyword, repeatedWordList, pathModelList);

                        // similar words list
                        List<String> similarWordList = topicService.getSimilarWords(topicKeywordList, childrenTopicNameList);
                        updateSimilarWordPathModelList3(keyword, similarWordList, pathModelList);

                    }else { // match the the FirstTopic's sub topics with topic keywords
                        // keyword -> firstTopic -> sub topics -> topic keywords -> topic
                        // repeated words list
                        if(!childrenTopicNameList.contains(keyword)){
                            childrenTopicNameList.add(keyword);
                        }
                        List<String> repeatedWordList = topicService.getRepeatedWordList(topicKeywordList, childrenTopicNameList);
                        updateRepeatedWordPathModelList3(keyword, theFirstTopic, repeatedWordList, pathModelList);

                        // similar words list
                        List<String> similarWordList = topicService.getSimilarWords(topicKeywordList, childrenTopicNameList);
                        updateSimilarWordPathModelList4(keyword, theFirstTopic, similarWordList, pathModelList);
                    }
                }

            }else{// didn't get zhiHuTopics
                //keyword -> topic keywords(content,title,similar) -> topic
                childrenTopicNameList.add(keyword);
                // repeated words list
                List<String> repeatedWordList = topicService.getRepeatedWordList(topicKeywordList, childrenTopicNameList);
                updateRepeatedWordPathModelList(keyword, repeatedWordList, pathModelList);
                // similar words list
                List<String> similarWordList = topicService.getSimilarWords(topicKeywordList, childrenTopicNameList);
                updateSimilarWordPathModelList(similarWordList, pathModelList);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return pathModelList;
    }


    private void updateRepeatedWordPathModelList(String start,List<String> wordList,List<PathModel> pathModelList){

        wordList.forEach( p -> {

            if(StringUtils.equals(start, p)){
                return;
            }

            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add( new Node(start) );
            nodes.add( new Node(p) );

            Set<Edge> edges = new HashSet<>() ;

            edges.add(new Edge(start, p, WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(p),
                    wordService.getWordVectorsByCache(start) )));

            PathModel model = new PathModel(nodes, edges) ;

            pathModelList.add( model );

        });
    }


    private void updateSimilarWordPathModelList(List<String> wordList,List<PathModel> pathModelList){

        wordList.forEach( p -> {

            String[] keywords = p.split("-");

            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add( new Node(keywords[0]) );
            nodes.add( new Node(keywords[1]) );

            Set<Edge> edges = new HashSet<>() ;

            edges.add(new Edge(keywords[0], keywords[1], WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(keywords[0]),
                    wordService.getWordVectorsByCache(keywords[1]) )));

            PathModel model = new PathModel(nodes, edges) ;

            pathModelList.add( model );

        });

    }

    private void updateSimilarWordPathModelList3(String start,List<String> wordList,List<PathModel> pathModelList){
        wordList.forEach(words -> {

            String[] keywords = words.split("-");

            if( StringUtils.equals(start, keywords[0]) ){
                LinkedList<Node> nodes = new LinkedList<>();
                nodes.add( new Node(keywords[0]) );
                nodes.add( new Node(keywords[1]) );
                Set<Edge> edges = new HashSet<>() ;
                edges.add(new Edge(keywords[0], keywords[1], WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache(keywords[0]),
                        wordService.getWordVectorsByCache(keywords[1]) )));
                PathModel model = new PathModel(nodes, edges) ;
                pathModelList.add( model );
                return;
            }else if(StringUtils.equals(start, keywords[1])){
                return;
            }

            LinkedList<Node> nodes = new LinkedList<>();

            nodes.add( new Node(start) ) ;
            nodes.add( new Node(keywords[0]) );
            nodes.add( new Node(keywords[1]) );

            Set<Edge> edges = new HashSet<>() ;

            edges.add(new Edge(start, keywords[0], WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(keywords[0]),
                    wordService.getWordVectorsByCache(start))));

            edges.add(new Edge(keywords[0], keywords[1], WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(keywords[0]),
                    wordService.getWordVectorsByCache(keywords[1]) )));

            PathModel model = new PathModel(nodes, edges) ;

            pathModelList.add( model );

        });

    }

    private void updateRepeatedWordPathModelList3(String start, String firstTopic,
                                                  List<String> wordList,
                                                  List<PathModel> pathModelList) {
        wordList.forEach(p -> {

            if( StringUtils.equals(firstTopic,p) ){
                LinkedList<Node> nodes = new LinkedList<>();
                nodes.add(new Node(start));
                nodes.add(new Node(firstTopic));
                Set<Edge> edges = new HashSet<>();
                edges.add(new Edge(start, firstTopic, WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache(start),
                        wordService.getWordVectorsByCache(firstTopic))));
                PathModel model = new PathModel(nodes, edges);
                pathModelList.add(model);
                return;
            }else if(StringUtils.equals(start,p)){
                return;
            }

            LinkedList<Node> nodes = new LinkedList<>();

            nodes.add(new Node(start));
            nodes.add(new Node(firstTopic));
            nodes.add(new Node(p));

            Set<Edge> edges = new HashSet<>();

            edges.add(new Edge(start, firstTopic, WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(p),
                    wordService.getWordVectorsByCache(firstTopic))));

            edges.add(new Edge(firstTopic, p, WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(p),
                    wordService.getWordVectorsByCache(firstTopic))));

            PathModel model = new PathModel(nodes, edges);

            pathModelList.add(model);

        });
    }

    private void updateSimilarWordPathModelList4(String start, String firstTopic,
                                                 List<String> wordList,
                                                 List<PathModel> pathModelList){
        wordList.forEach(words -> {
            String[] keywords = words.split("-");
            if(StringUtils.equals(start, keywords[0])){
                LinkedList<Node> nodes = new LinkedList<>();
                nodes.add( new Node(start) ) ;
                nodes.add( new Node(keywords[1]) );
                Set<Edge> edges = new HashSet<>() ;
                edges.add(new Edge(start, keywords[1], WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache(start),
                        wordService.getWordVectorsByCache(keywords[1]))));
                PathModel model = new PathModel(nodes, edges) ;
                pathModelList.add( model );
                return;
            }
            if(StringUtils.equals(start, keywords[1])){
                return;
            }
            if(StringUtils.equals(firstTopic, keywords[0])){
                LinkedList<Node> nodes = new LinkedList<>();
                nodes.add( new Node(start) ) ;
                nodes.add( new Node(firstTopic) ) ;
                nodes.add( new Node(keywords[1]) ) ;

                Set<Edge> edges = new HashSet<>() ;

                edges.add(new Edge(start, firstTopic, WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache( start ),
                        wordService.getWordVectorsByCache( firstTopic ))));

                edges.add(new Edge(firstTopic, keywords[1], WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache(firstTopic),
                        wordService.getWordVectorsByCache(keywords[1]))));

                PathModel model = new PathModel(nodes, edges) ;
                pathModelList.add( model );
                return;
            }
            if( StringUtils.equals(firstTopic, keywords[1]) ){
                LinkedList<Node> nodes = new LinkedList<>();
                nodes.add( new Node(start) ) ;
                nodes.add( new Node(firstTopic) ) ;

                Set<Edge> edges = new HashSet<>() ;
                edges.add(new Edge(start, firstTopic, WordVectorHelper.getSimilarity(
                        wordService.getWordVectorsByCache(start),
                        wordService.getWordVectorsByCache(firstTopic))));
                PathModel model = new PathModel(nodes, edges) ;

                pathModelList.add( model );

                return;
            }

            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add( new Node(start) ) ;
            nodes.add( new Node(firstTopic) ) ;
            nodes.add( new Node(keywords[0]) );
            nodes.add( new Node(keywords[1]) );

            Set<Edge> edges = new HashSet<>() ;

            edges.add(new Edge(start, firstTopic, WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(start),
                    wordService.getWordVectorsByCache(firstTopic))));

            edges.add(new Edge(firstTopic, keywords[0], WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(keywords[0]),
                    wordService.getWordVectorsByCache(firstTopic))));

            edges.add(new Edge(keywords[0], keywords[1], WordVectorHelper.getSimilarity(
                    wordService.getWordVectorsByCache(keywords[0]),
                    wordService.getWordVectorsByCache(keywords[1]) )));

            PathModel model = new PathModel(nodes, edges) ;

            pathModelList.add( model );

        });
    }


}
