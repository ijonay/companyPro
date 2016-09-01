package com.zc.utility;

import com.zc.model.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by 张镇强 on 2016/8/16 16:28.
 */
public final class WordVectorHelper {
    private final static int MAX_SIZE = 50;
    private static HashMap<String, HashMap<String, Float>> filterList;

//    static {
//        try {
//            filterList = loadFilterList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static HashMap<String, HashMap<String, Float>> loadFilterList()
            throws IOException {
        HashMap<String, HashMap<String, Float>> result = new HashMap<String, HashMap<String, Float>>();
        for (String key : ResourceDict.MODEL_DICT.keySet()) {
            WordModel wordModel = ResourceDict.MODEL_DICT.get(key);
            HashMap<String, Float> wordPair = new HashMap<String, Float>();
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            InputStream inputStream = resourceLoader.getResource(
                    wordModel.getFilterListPath()).getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(
                    inputStream, "UTF-8"));
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] splitedArray = line.split("\t");
                if (splitedArray != null && splitedArray.length > 0) {
                    wordPair.put(splitedArray[0],
                            Float.parseFloat(splitedArray[1]));
                }
            }

            result.put(key, wordPair);
            bReader.close();
        }

        return result;
    }

    /**
     * 根据model路径 获取模型对象
     *
     * @param modelPath model路径
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static Map<String, float[]> loadModel(String modelPath)
            throws NumberFormatException, IOException {
        Map<String, float[]> wordMap = new HashMap<String, float[]>();
        DataInputStream dis = null;
        BufferedInputStream bis = null;
        double len = 0;
        float vector = 0;
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            InputStream inputStream = resourceLoader.getResource(modelPath)
                    .getInputStream();
            bis = new BufferedInputStream(inputStream);
            dis = new DataInputStream(bis);
            // //读取词数
            int words = Integer.parseInt(readString(dis));
            // //大小
            int size = Integer.parseInt(readString(dis));

//            words = 10000;

            String word;
            float[] vectors = null;
            for (int i = 0; i < words; i++) {
                word = readString(dis);
                vectors = new float[size];
                len = 0;
                for (int j = 0; j < size; j++) {
                    vector = readFloat(dis);
                    len += vector * vector;
                    vectors[j] = (float) vector;
                }
                len = Math.sqrt(len);

                for (int j = 0; j < vectors.length; j++) {
                    vectors[j] = (float) (vectors[j] / len);
                }
                wordMap.put(word, vectors);
                dis.read();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            bis.close();
            dis.close();
            return wordMap;
        }
    }

    /**
     * 得到近义词
     *
     * @param word
     * @return
     */
    public static Set<WordEntry> getDistance(String word,
                                             Map<String, float[]> wordMap, int topNSize, float score) {
        float[] wordVector = getWordVector(wordMap, word);
        if (wordVector == null) {
            return null;
        }
        Set<Map.Entry<String, float[]>> entrySet = wordMap.entrySet();
        float[] tempVector = null;
        List<WordEntry> wordEntrys = new ArrayList<WordEntry>(topNSize);
        String name = null;
        for (Map.Entry<String, float[]> entry : entrySet) {
            name = entry.getKey();
            if (name.equals(word)) {
                continue;
            }

//            if (!filterList.get(modelName).containsKey(name)) {
//                continue;
//            }
//            float weight = filterList.get(modelName).get(name);
            tempVector = entry.getValue();
            float dist = getSimilarity(wordVector, tempVector);
            if (dist >= score)
                insertTopN(name, dist, wordEntrys, topNSize);
        }
        return new TreeSet<WordEntry>(wordEntrys);
    }

    /**
     * 获取两个词的相似度
     *
     * @param sourceVector 源词汇向量
     * @param targetVector 目标词汇向量
     * @return
     */
    public static float getSimilarity(float[] sourceVector, float[] targetVector) {
        if (sourceVector == null || targetVector == null) return 0;

        float dist = 0;
        for (int i = 0; i < sourceVector.length; i++) {
            dist += sourceVector[i] * targetVector[i];
        }
        return dist;
    }

    public static VertexEdgeModel breadthFirstSearch(Map<String, float[]> modelMap,
                                                     String clueWord, int topNSize, float score, int maxLength,
                                                     String modelName) {
        List<VertexModel> vertexs = new ArrayList<VertexModel>();
        List<EdgeModel> edges = new ArrayList<EdgeModel>();
        VertexEdgeModel vertexEdgeModel = new VertexEdgeModel(clueWord,
                vertexs, edges);

        SimpleWeightedGraph<String, DefaultWeightedEdge> resultGraph = new SimpleWeightedGraph<String, DefaultWeightedEdge>(
                DefaultWeightedEdge.class);
        HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
        Queue<WordNode> queue = new LinkedList<WordNode>();

        queue.add(new WordNode(clueWord, 0));
        resultGraph.addVertex(clueWord);
        tempMap.put(clueWord, 0);
        vertexs.add(new VertexModel(clueWord, 1));

        while (!queue.isEmpty()) {
            WordNode vertex = queue.poll();
            if (vertex.getDepth() > maxLength)
                break;

            Set<WordEntry> words = getDistance(vertex.getValue(), modelMap,
                    topNSize, score);
            if (words == null)
                break;

            for (WordEntry w : words) {
                if (!resultGraph.containsVertex(w.name)) {
                    queue.add(new WordNode(w.name, vertex.getDepth() + 1));
                    tempMap.put(w.name, vertex.getDepth() + 1);
                    resultGraph.addVertex(w.name);
                    resultGraph.addEdge(vertex.getValue(), w.name);

                    vertexs.add(new VertexModel(w.name, 1));
                    edges.add(new EdgeModel(vertex.getValue(), w.name, w.score));
                } else {
                    if (tempMap.get(w.name) > vertex.getDepth()) {
                        resultGraph.addEdge(vertex.getValue(), w.name);

                        edges.add(new EdgeModel(vertex.getValue(), w.name,
                                w.score));
                    }
                }
            }
        }


//        AllPaths allPaths = new AllPaths(resultGraph);
//        allPaths.enumerate("尼克", "狐狸");
        return vertexEdgeModel;
    }

    /**
     * 得到词向量
     *
     * @param word
     * @return
     */
    public static float[] getWordVector(Map<String, float[]> wordMap, String word) {
        return wordMap.get(word);
    }

    private static void insertTopN(String name, float score,
                                   List<WordEntry> wordsEntrys, int topNSize) {
        if (wordsEntrys.size() < topNSize) {
            wordsEntrys.add(new WordEntry(name, score));
            return;
        }
        float min = Float.MAX_VALUE;
        int minOffe = 0;
        for (int i = 0; i < topNSize; i++) {
            WordEntry wordEntry = wordsEntrys.get(i);
            if (min > wordEntry.score) {
                min = wordEntry.score;
                minOffe = i;
            }
        }

        if (score > min) {
            wordsEntrys.set(minOffe, new WordEntry(name, score));
        }

    }

    /**
     * 读取一个字符串
     *
     * @param dis
     * @return
     * @throws IOException
     */
    private static String readString(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[MAX_SIZE];
        byte b = dis.readByte();
        int i = -1;
        StringBuilder sb = new StringBuilder();
        while (b != 32 && b != 10) {
            i++;
            bytes[i] = b;
            b = dis.readByte();
            if (i == 49) {
                sb.append(new String(bytes));
                i = -1;
                bytes = new byte[MAX_SIZE];
            }
        }
        sb.append(new String(bytes, 0, i + 1, Charset.forName("UTF-8")));
        return sb.toString();
    }

    private static float readFloat(InputStream is) throws IOException {
        byte[] bytes = new byte[4];
        is.read(bytes);
        return getFloat(bytes);
    }

    /**
     * 读取一个float
     *
     * @param b
     * @return
     */
    private static float getFloat(byte[] b) {
        int accum = 0;
        accum = accum | (b[0] & 0xff) << 0;
        accum = accum | (b[1] & 0xff) << 8;
        accum = accum | (b[2] & 0xff) << 16;
        accum = accum | (b[3] & 0xff) << 24;
        return Float.intBitsToFloat(accum);
    }
}
