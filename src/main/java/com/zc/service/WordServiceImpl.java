package com.zc.service;

import com.alibaba.fastjson.JSON;
import com.zc.WordRedisModel;
import com.zc.bean.*;
import com.zc.dao.WordDao;
import com.zc.model.*;
import com.zc.utility.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by polun on 2016/7/8.
 */
@Service
@Lazy(false)
public class WordServiceImpl implements WordService {

    private static Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);

    @Autowired
    private TopicService topicServicetemp;
    private static TopicService topicService;

    @Autowired
    private WeiboService weiboServicetemp;
    private static WeiboService weiboService;

    @Autowired
    private WordService wordServicetemp;
    private static WordService wordService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ZCRedisService<float[]> redisService;

    @Autowired
    private WordDao dao;

    private static Map<String, float[]> modelMap = null;
    private static Map<Integer, TopicModel> topicMap = new HashMap<Integer, TopicModel>();
    private static Map<Long, WeiboModel> weiboMap = new HashMap<Long, WeiboModel>();
    private static Map<Integer, float[]> wordMap = new HashMap<Integer, float[]>();

    @PostConstruct
    public void init() {
        topicService = topicServicetemp;
        weiboService = weiboServicetemp;
        wordService = wordServicetemp;


//        loadMaps();


//        loadTopicMap();
        // loadWordMap();

        // loadMaps();
        // loadTopicMap();


    }

    @Deprecated
    public Map<String, float[]> getModelMap() {
        if (modelMap == null) {
            loadMaps();
        }
        return modelMap;
    }

    @Override
    public float[] getWordVectorsByCache(String key) {

        String keyPrefix = PropertyHelper.getValue(Constant
                        .CONFIG_PROPERTIES,
                Constant.WORD_VECTORS_KEY_PREFIX);

        return redisService.getCacheObject(keyPrefix + key);

    }

    public Map<Integer, TopicModel> getTopicMap() {
        return topicMap;
    }


    @Deprecated
    private void loadMaps() {
        try {
            modelMap = WordVectorHelper.loadModel(
                    PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MODEL_BIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 刷新redis中语料库信息 如果redis不存在数据则重新加载数据如果有则不做任何操作
     *
     * @return
     */
    private void refreshMaps() {

        String keyPrefix = Constant.WORD_VECTORS_KEY_PREFIX;
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        if (keys.size() <= 0) {
            cache_UpdateWordVectors();
        }

    }

    /**
     * 更新redis中的语料库信息 每调用一次更新一次redis
     */
    public void cache_UpdateWordVectors() {
        try {

            long readTime = System.currentTimeMillis();

            Map<String, float[]> wordMap = WordVectorHelper.loadModel(PropertyHelper.getValue(Constant
                    .CONFIG_PROPERTIES, Constant
                    .MODEL_BIN));

            if (Objects.nonNull(wordMap)) {

                String keyPrefix = PropertyHelper.getValue(Constant
                                .CONFIG_PROPERTIES,
                        Constant.WORD_VECTORS_KEY_PREFIX);

                Set<String> keys = redisTemplate.keys(keyPrefix + "*");

                long startTime = System.currentTimeMillis();

                redisTemplate.execute(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations operations) throws DataAccessException {

                        operations.multi();

                        operations.delete(keys);


                        Map<String, float[]> wordMapTemp = new HashMap<String, float[]>();

                        wordMap.forEach((k, v) -> wordMapTemp.put(keyPrefix + k, v));


                        operations.opsForValue().multiSet(wordMapTemp);

//                        wordMap.forEach((k, v) -> operations.opsForValue().set(PropertyHelper.getValue(Constant
//                                        .CONFIG_PROPERTIES,
//                                keyPrefix) + k, v v));

                        return operations.exec();

                    }
                });
                long endTime = System.currentTimeMillis();

                System.out.println("当前开始时间为：" + readTime + "秒 执行入库开始时间为：" + startTime + "秒 结束时间为：" + endTime + "秒");
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void cache_UpdateWordRoundPoints() {
        try {

            Map<String, float[]> wordMap =
                    WordVectorHelper.loadModel(
                            PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MODEL_BIN));

            Set<Map.Entry<String, float[]>> wordSet = wordMap.entrySet();
            int count = 1;
            long start = System.currentTimeMillis();
            for (Map.Entry<String, float[]> entry : wordSet) {
                String name = entry.getKey();

                if (StringHelper.isEmpty(name)) {
                    continue;
                }
                Set<WordEntry> neighbors =
                        WordVectorHelper.getDistance(name, wordMap, 25, 0.4f);

                if (neighbors.size() == 0) {
                    continue;
                }

                neighbors.forEach(n -> {
                    WordRedisModel w = new WordRedisModel(n.name, n.score);
                    redisTemplate.boundZSetOps(Constant.WORDR_EDISKEY_PREFIX_KEY + name).add(w, n.score);
                });


                if (count++ % 100 == 0) {
                    System.out.println("-----------:" + count + "||" + (System.currentTimeMillis() - start));
                    logger.info("-----------:" + count + "||" + (System.currentTimeMillis() - start));
                }
            }

            System.out.println("耗时:" + (System.currentTimeMillis() - start));
            logger.info("耗时:" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void loadTopicMap() {
        List<Topic> list = getTopicList(1000, false);
        for (Topic item : list) {
            if (StringUtils.isNoneEmpty(item.getCoordinate())) {
                TopicModel model = item.getModel();
                float[] coordiante = CommonHelper.stringToFloatArray(item
                        .getCoordinate());
                model.setCoordinate(coordiante);
                topicMap.put(item.getId(), model);
            }
        }
    }

    /**
     * 初始更新topic的coordinate
     */
    private static void InitUpdateTopicCoordinate() {
        try {
            List<Topic> list = getTopicList(1000, true);
            List<Topic> listTemp = new LinkedList<Topic>();
            for (int i = 0; i < list.size(); i++) {
                Topic topic = list.get(i);
                float[] topic_vectors = getTopicVector(topic);
                if (topic_vectors != null)
                    topic.setCoordinate(JSON.toJSONString(topic_vectors));
                else
                    topic_vectors = new float[200];
                listTemp.add(topic);
                /*
                 * TopicModel model = topic.getModel();
                 * model.setCoordinate(topic_vectors);
                 * topicMap.put(topic.getId(), model);
                 */
                if (i % 1000 == 0 || i == list.size() - 1) {
                    topicService.batchUpdate(listTemp);
                    listTemp = new LinkedList<Topic>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadWordMap() {
        try {
            List<Word> list = getWordList(1000);
            for (Word word : list) {
                wordMap.put(word.getId(),
                        CommonHelper.stringToFloatArray(word.getCoordinate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static float[] getWordCoordinate(Integer wordid) {
        return CommonHelper.stringToFloatArray(wordService.get(wordid)
                .getCoordinate());
    }

    private static void loadWeiMap() {
        try {
            int pageSize = 2000;
            int weiboCount = weiboService.getItemCount();
            int pageCount = (int) Math.ceil(weiboCount / pageSize);
            List<Weibo> list = new LinkedList<Weibo>();
            for (int i = 0; i < pageCount; i++) {
                list = weiboService.getList(pageSize, i * pageSize);
                if (list != null && list.size() > 0) {
                    for (Weibo w : list) {
                        float[] weibo_vectors = getWeiboVector(w);
                        if (weibo_vectors != null)
                            w.setCoordinate(JSON.toJSONString(weibo_vectors));
                        else
                            weibo_vectors = new float[200];

                        WeiboModel model = w.getModel();
                        model.setCoordinate(weibo_vectors);
                        weiboMap.put(w.getId().longValue(), model);
                    }
                    weiboService.batchUpdate(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private static void loadTopicMap() {
    // String path = ResourceDict.Topic_DIRECTORY.get("dict");
    // try {
    // List<File> list = ZCFile.readResourceFile(path, "txt");
    // for (File file : list) {
    // float[] topic_vectors = getTopicVector(file);
    // topicMap.put(file.getName(), topic_vectors);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    private static List<Word> getWordList(int pageSize) {
        List<Word> list = new LinkedList<Word>();
        Integer wordCount = wordService.getItemCount();
        int pageCount = (int) Math.ceil(wordCount / (double) pageSize);
        for (int i = 0; i < pageCount; i++) {
            List<Word> pageList = wordService.getList(pageSize, i * pageSize);
            if (pageList != null && pageList.size() > 0)
                list.addAll(pageList);
        }
        return list;
    }

    private static List<Topic> getTopicList(int pageSize, boolean isInit) {
        List<Topic> list = new LinkedList<Topic>();
        Integer topicCount = topicService.getItemCount();
        int pageCount = (int) Math.ceil(topicCount / (double) pageSize);
        for (int i = 0; i < pageCount; i++) {
            List<Topic> pageList = new LinkedList<Topic>();
            if (isInit)
                pageList = topicService.getTopicWordList(pageSize, i
                        * pageSize);
            else
                pageList = topicService.getList(pageSize, i
                        * pageSize);
            if (pageList != null && pageList.size() > 0)
                list.addAll(pageList);
        }
        return list;
    }

    public static float[] getTopicVector(Topic topic) {
        List<WordDataRelations> list = topic.getWords();
        List<KeyWord> wordEntrys = new LinkedList<KeyWord>();
        if (list != null && list.size() > 0) {
            for (WordDataRelations r : list) {
                wordEntrys.add(new KeyWord() {
                    {
                        setWeight(r.getScore());
                        setCoordinate(getWordCoordinate(r.getWordId()));
                    }
                });
            }
        }

        return getKeywordsVector(wordEntrys);
    }

    public static float[] getWeiboVector(Weibo weibo) {
        List<KeyWord> wordEntrys = JSON.parseArray(weibo.getKeyWords(),
                KeyWord.class);
        return getKeywordsVector(wordEntrys);
    }

    private static float[] getKeywordsVector(List<KeyWord> wordEntrys) {
        if (wordEntrys == null || wordEntrys.size() == 0)
            return null;
        float[] result = new float[200];
        for (KeyWord wordEntry : wordEntrys) {
            if (wordEntry == null)
                continue;
            Float weight = wordEntry.getWeight();
            float[] vectors = wordEntry.getCoordinate();
            if (vectors == null)
                vectors = new float[200];
            for (int i = 0; i < vectors.length; i++) {
                float preVector = result[i];
                float vector = vectors[i];
                result[i] = preVector + weight * vector;
            }
        }
        return result;
    }

    private static float[] getTopicVector(File file) throws Exception {
        float[] result = new float[200];
        List<String> wordEntrys = ZCFile.ReadTextFile(file);
        for (String wordEntry : wordEntrys) {
            if (wordEntry == null)
                continue;
            String[] split = wordEntry.split("\\u0024");
            String title = split[0];
            Float weight = Float.parseFloat(split[2]);
            float[] vectors = modelMap.get(title);
            if (vectors == null)
                vectors = new float[200];
            for (int i = 0; i < vectors.length; i++) {
                float preVector = result[i];
                float vector = vectors[i];
                result[i] = preVector + weight * vector;
            }
        }
        return result;
    }

    public VertexEdgeModel getWords(String modelName, String clueWord,
                                    int topN, float relevancy, int length) throws IOException {

        Map<String, float[]> modelMap = WordVectorHelper
                .loadModel(ResourceDict.MODEL_DICT.get(modelName)
                        .getModelPath());
        if (modelMap == null)
            return null;

        return WordVectorHelper.breadthFirstSearch(modelMap, clueWord, topN,
                relevancy, length, modelName);
    }

    public List<TopicModel> getTopicSimilarity(String sourceWord) {
        List<TopicModel> result = new LinkedList<TopicModel>();
        float[] sourceVectors = modelMap.get(sourceWord);
        if (sourceVectors == null) {
            return null;
        }

        for (Integer id : topicMap.keySet()) {
            TopicModel model = topicMap.get(id);

            float score = WordVectorHelper.getSimilarity(model.getCoordinate(),
                    sourceVectors);
            model.setScore(score);
//            TopicModel newModel = getNewModel(id, model);
            result.add(model);
        }
        result = result
                .stream()
                .sorted((object1, object2) -> object2.getScore().compareTo(
                        object1.getScore())).collect(Collectors.toList());
        return result;
    }

    // 看不明白为什么需要这个函数
    private TopicModel getNewModel(Integer id, TopicModel model) {
        TopicModel newModel = new TopicModel();
        newModel.setId(id);
        newModel.setTitle(model.getTitle());
        newModel.setScore(model.getScore());
        return newModel;
    }

    @Override
    @Deprecated
    public float[] getWordVector(String word) {
        float[] result = new float[200];
        if (modelMap.containsKey(word))
            result = modelMap.get(word);
        return result;
    }

    public void writeArff(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    }

    public List<List<ClusterModel>> KMeans(File arfffile, File outFile,
                                           Integer clusterNum, List<String> listWords) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(arfffile);
        Instances ins = loader.getDataSet();

        EuclideanDistance distance = new EuclideanDistance();

        SimpleKMeans km = new SimpleKMeans();
        km.setNumClusters(clusterNum);
        km.setPreserveInstancesOrder(true);
        km.setDistanceFunction(distance);
        km.buildClusterer(ins);

        Instances centers = km.getClusterCentroids();
        List<List<ClusterModel>> result = new LinkedList<List<ClusterModel>>();
        for (int i = 0; i < centers.numInstances(); i++) {
            List<ClusterModel> singleClusterList = new LinkedList<ClusterModel>();
            Instance centerinst = centers.instance(i);
            for (int j = 0; j < ins.numInstances(); j++) {
                Instance sampleinst = ins.instance(j);
                double score = distance.distance(sampleinst, centerinst);
                ClusterModel model = new ClusterModel();
                model.setClusterId(i);
                model.setDistance(score);
                model.setWord(listWords.get(j));
                singleClusterList.add(model);
            }
            singleClusterList = singleClusterList
                    .stream()
                    .sorted((object1, object2) -> object1.getDistance()
                            .compareTo(object2.getDistance()))
                    .collect(Collectors.toList()).subList(0, 10);
            result.add(singleClusterList);
        }
        return result;
    }

    public List<List<ClusterModel>> EM(File arfffile, File outFile,
                                       List<String> listWords) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(arfffile);
        Instances ins = loader.getDataSet();

        EuclideanDistance distance = new EuclideanDistance();

        EM em = new EM();
        em.setNumClusters(10);
        em.buildClusterer(ins);

        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(em);
        eval.evaluateClusterer(ins);

        eval.clusterResultsToString();
        // Instances centers = em.get
        // List<List<ClusterModel>> result = new
        // LinkedList<List<ClusterModel>>();
        // for (int i = 0; i < centers.numInstances(); i++) {
        // List<ClusterModel> singleClusterList = new
        // LinkedList<ClusterModel>();
        // Instance centerinst = centers.instance(i);
        // for (int j = 0; j < ins.numInstances(); j++) {
        // Instance sampleinst = ins.instance(j);
        // double score = distance.distance(sampleinst, centerinst);
        // ClusterModel model=new ClusterModel();
        // model.setClusterId(i);
        // model.setDistance(score);
        // model.setWord(listWords.get(j));
        // singleClusterList.add(model);
        // }
        // singleClusterList=singleClusterList.stream().sorted((object1,
        // object2) -> object2.getDistance().compareTo(
        // object1.getDistance())).collect(Collectors.toList()).subList(0, 10);
        // result.add(singleClusterList);
        // }
        return null;
    }

    @Override
    public Integer add(Word bean) {
        return dao.add(bean);
    }

    @Override
    public Integer del(Integer id) {
        return dao.del(id);
    }

    @Override
    public Integer update(Word bean) {
        return dao.update(bean);
    }

    @Override
    public Word get(Integer id) {
        return dao.get(id);
    }

    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }

    @Override
    public List<Word> getList(Integer pageSize, Integer rowStart) {
        return dao.getList(pageSize, rowStart);
    }

}
