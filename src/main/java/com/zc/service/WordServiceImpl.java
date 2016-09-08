package com.zc.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.ManhattanDistance;
import weka.core.converters.ArffLoader;

import com.alibaba.fastjson.JSON;
import com.zc.bean.KeyWord;
import com.zc.bean.Topic;
import com.zc.model.ClusterModel;
import com.zc.model.TopicModel;
import com.zc.model.VertexEdgeModel;
import com.zc.utility.ResourceDict;
import com.zc.utility.WordVectorHelper;
import com.zc.utility.ZCFile;

/**
 * Created by polun on 2016/7/8.
 */
@Service
@Lazy(false)
public class WordServiceImpl implements WordService {
    @Autowired
    private TopicService topicServicetemp;
    private static TopicService topicService;
    private static Map<String, float[]> modelMap = new HashMap<String, float[]>();
    private static Map<Long, TopicModel> topicMap = new HashMap<Long, TopicModel>();

    @PostConstruct
    public void init() {
        topicService = topicServicetemp;
        loadMaps();
        loadTopicMap();
    }

    private static void loadTopicMap() {
        try {
            List<Topic> list = getTopicList(1000);
            for (Topic topic : list) {
                float[] topic_vectors = getTopicVector(topic);
                if (topic_vectors != null)
                    topic.setCoordinate(JSON.toJSONString(topic_vectors));
                else
                    topic_vectors = new float[200];

                TopicModel model = new TopicModel();
                model.setTitle(topic.getTitle());
                model.setCoordinate(topic_vectors);
                topicMap.put(topic.getId(), model);
            }
            // topicService.batchUpdate(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadMaps() {
        try {
            modelMap = WordVectorHelper.loadModel(ResourceDict.Topic_Dict
                    .get("cbow0"));
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
    private static List<Topic> getTopicList(int pageSize) {
        List<Topic> list = new LinkedList<Topic>();
        Integer topicCount = topicService.getItemCount();
        int pageCount = (int) Math.ceil(topicCount / (double) pageSize);
        for (int i = 0; i < pageCount; i++) {
            List<Topic> pageList = topicService.getList(pageSize, i * pageSize);
            if (pageList != null && pageList.size() > 0)
                list.addAll(pageList);
        }
        return list;
    }

    public static float[] getTopicVector(Topic topic) {
        float[] result = new float[200];
        List<KeyWord> wordEntrys = JSON.parseArray(topic.getKeywords(),
                KeyWord.class);
        if (wordEntrys == null || wordEntrys.size() == 0)
            return null;
        for (KeyWord wordEntry : wordEntrys) {
            if (wordEntry == null)
                continue;
            Float weight = wordEntry.getWeight();
            float[] vectors = modelMap.get(wordEntry.getWord());
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

        for (Long id : topicMap.keySet()) {
            TopicModel model = topicMap.get(id);

            float score = WordVectorHelper.getSimilarity(model.getCoordinate(),
                    sourceVectors);
            model.setScore(score);
            TopicModel newModel = getNewModel(id, model);
            result.add(newModel);
        }
        result = result
                .stream()
                .sorted((object1, object2) -> object2.getScore().compareTo(
                        object1.getScore())).collect(Collectors.toList());
        return result;
    }

    private TopicModel getNewModel(Long id, TopicModel model) {
        TopicModel newModel = new TopicModel();
        newModel.setId(id);
        newModel.setTitle(model.getTitle());
        newModel.setScore(model.getScore());
        return newModel;
    }

    @Override
    public float[] getWordVector(String word) {
        float[] result = new float[200];
        if (modelMap.containsKey(word))
            result = modelMap.get(word);
        return result;
    }

    public void writeArff(File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    }

    public List<List<ClusterModel>>  KMeans(File arfffile, File outFile, List<String> listWords)
            throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(arfffile);
        Instances ins = loader.getDataSet();

        EuclideanDistance distance = new EuclideanDistance();

        SimpleKMeans km = new SimpleKMeans();
        km.setNumClusters(10);
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
                ClusterModel model=new ClusterModel();
                model.setClusterId(i);
                model.setDistance(score);
                model.setWord(listWords.get(j));
                singleClusterList.add(model);
            }
            singleClusterList=singleClusterList.stream().sorted((object1, object2) -> object1.getDistance().compareTo(
                        object2.getDistance())).collect(Collectors.toList()).subList(0, 10);
            result.add(singleClusterList);
        }
        return result;
    }
    
    
    public List<List<ClusterModel>>  EM(File arfffile, File outFile, List<String> listWords)
            throws Exception {
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
    
}
