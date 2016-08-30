package com.zc.service;

import com.zc.model.WordEntry;
import com.zc.utility.CommonHelper;
import com.zc.utility.ResourceDict;
import com.zc.utility.WordVectorHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.util.*;

/**
 * 寻找图中两点的所有路径
 * Created by 张镇强 on 2016/7/14 16:18.
 */
public class AllPaths {
    private final float SIMILARITY_THRESHOLD = 0.46f;
    private final int TOPNSIZE = 10;
    private final float MINSCORE = 0.41f;
    private final int MAX_PATHLENGTH = 8;
    private final float DISSIMILARITY_THRESHOLD = 0.4f; // 不相似阈值
    private List<Stack<String>> pathList;
    private String modelName;
    private boolean isFirst = true;

    private Stack<String> path = new Stack<>();
    private Set<String> onPath = new HashSet<>();
    private Map<String, float[]> wordMap;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        AllPaths paths = new AllPaths();
        double[] targetVectorD = {
                -0.043273643,
                -0.022552885, 0.014952003, 0.06760047, 0.04724256, -0.035661884, -0.012260031, 0.010878087, -0.031092227, -0.0109199975, 0.0054246117, -0.026690993, -0.0036361073, -0.088428035, -0.014244156, -0.024067605, 0.0042233057, -0.027495975, -0.02823688, -0.07548669, -0.04116888, 7.1898196E-4, 0.019118039, 0.017411519, -0.084153906, -0.067324854, 0.015621694, 0.006789744, -0.12207826, -0.024575587, -0.01117107, 0.0013649389, -0.038070563, -0.0974351, -0.009001287, 0.12823784, 0.06434568, 0.03517963, 0.08529101, 0.035485256, -0.0014887812, 0.013785951, -8.317125E-4, -0.042108826, -0.06384534, 0.034708895, -0.009429252, 0.022883493, -0.028049542, -0.055317856, -0.08016047, 0.022365112, 0.02377909, -0.08891772, -0.00998268, 0.024471965, -0.003586717, 0.037750468, -0.018431855, -0.0818859, -0.041004047, 0.047901627, -0.016134933, 0.011661135, -0.07753142, 0.04396339, -0.09501088, -0.028634181, -0.00499187, 0.006086475, 0.03431032, -0.017169688, -4.7077687E-4, -0.08312131, 0.0012984194, 0.02999435, -0.005002011, 0.017590407, 0.015750568, -0.0034542507, 0.020260692, 0.0037096809, 0.047207575, -0.057951957, 0.019499375, -0.005626858, -0.050523564, -0.018094342, 0.078017406, -0.08667223, 0.05421561, 0.02985927, 0.085683346, -0.056126967, -0.02314244, -0.003499396, -0.0052314554, 0.017806435, 0.033681925, -0.039892234, 0.03711327, 0.0056341765, 0.03162312, -0.0070736227, 0.08521336, 0.044341695, -0.027961582, 0.006197438, -0.041255824, 0.011318918, 0.048467156, 0.013908686, -0.040596902, 0.02578852, -0.0038023344, -7.2089117E-4, -0.002559402, 0.009186564, 0.03291042, 0.03601135, 0.07352746, 0.011766995, 0.03575805, -0.052436758, -0.05403894, 0.038441427, 0.078767106, 0.0044571124, 0.006729225, 0.07293842, -0.061305452, -0.093875624, 0.076576345, 0.014511507, -0.0026297988, 0.04361932, -0.093502864, 0.01337614, 0.02319277, 0.06824401, -0.046306767, -0.0037474171, 0.05955002, 0.053527925, -0.014878962, -0.013424709, 0.031090308, 0.018385882, -0.010757645, 0.013551867, -0.0070817545, 0.0269175, -0.04068715, -0.010941245, 0.01818889, -0.050663114, 0.007986968, -0.059303515, 0.027059447, -0.04305593, -0.0332144, -0.04683486, -0.012410205, -0.030653493, 0.029636841, -0.060025733, -0.044314053, -0.026685433, 0.040699188, -0.12834558, -0.0044870474, 0.05623457, 0.0028061573, -0.08062653, -0.11851636, -0.046048477, 0.10381967, -0.0062614516, 0.060411632, -0.08020093, 0.02230212, -0.09145543, -0.066357225, -0.058727913, 0.06001523, 0.06814516, -0.0066864146, 0.059590008, 0.078928486, 0.05787709, -0.004620747, -0.060219582, -0.012477284, 9.900947E-4, 0.036122188, -0.008576997, -0.032902982, 0.041823387, 0.028999671, -0.01993852
        };

        float[] targetVector = new float[targetVectorD.length];
        for (int i = 0; i < targetVectorD.length; i++) {
            targetVector[i] = (float) targetVectorD[i];
        }

        List<Stack<String>> allPaths = paths.getAllPath("霍建华", targetVector);
        allPaths.forEach(p -> {
            System.out.println(p);
        });

        System.out.println("Done!!!");
        long endTime = System.nanoTime();
        System.out.println("运行时间:" + (endTime - startTime) + " ns");
    }

    public AllPaths() {
        try {
            this.modelName = "doubanweibo1";
            this.wordMap = WordVectorHelper.loadModel(ResourceDict.MODEL_DICT.get(modelName).getModelPath());
        } catch (IOException e) {
            // TODO: 2016/8/23  处理异常
        }
        this.pathList = new ArrayList<>();
    }


    public List<Stack<String>> getAllPath(String start, float[] targetVector) {
        generatePath(start, targetVector);

        return this.pathList;
    }


    private void generatePath(String start, float[] targetVector) {
        path.push(start);
        onPath.add(start);


        if (path.size() <= MAX_PATHLENGTH) {
            if (isSatisfied(start, targetVector)) {
                Stack<String> temp = new Stack<>();
                temp.addAll(path);
                System.out.println(temp);
                System.out.println("------------------------");
                this.pathList.add(temp);
                if (isFirst) {
                    isFirst = false;
                    runRecursion(start, targetVector);
                }
            } else {
                runRecursion(start, targetVector);
            }
        }

        path.pop();
        onPath.remove(start);
    }

    private void runRecursion(String start, float[] targetVector) {
        Set<WordEntry> neighbors =
                WordVectorHelper.getDistance(start, this.wordMap, TOPNSIZE, MINSCORE);
        if (neighbors != null) {
            LinkedList<WordEntry> tempNeighbors = getSortedWordEntryList(neighbors, targetVector);
            for (WordEntry w : tempNeighbors) {
                if (!StringUtils.isEmpty(w.name) && !isDisSimilarity(start, w.name) && !onPath.contains(w.name)) {
                    generatePath(w.name, targetVector);
                }
            }
        }
    }

    private boolean isDisSimilarity(String start, String target) {
        float[] targetVector = this.wordMap.get(target);
        return getSimilarity(start, targetVector) <= DISSIMILARITY_THRESHOLD;
    }

    private boolean isSatisfied(String start, float[] targetVector) {
        return getSimilarity(start, targetVector) >= SIMILARITY_THRESHOLD;
    }

    private float getSimilarity(String start, float[] targetVector) {
        float[] startVector = this.wordMap.get(start);
        float similarity = WordVectorHelper.getSimilarity(startVector, targetVector);
        return similarity;
    }

    private LinkedList<WordEntry> getSortedWordEntryList(Set<WordEntry> neighbors, float[] targetVector) {
        LinkedList<WordEntry> list = new LinkedList(neighbors);
        HashMap<String, Float> t = new HashMap<>();

        Collections.sort(list, (left, right) ->
                CommonHelper.compare(
                        WordVectorHelper.getSimilarity(this.wordMap.get(right.name), targetVector),
                        WordVectorHelper.getSimilarity(this.wordMap.get(left.name), targetVector)
                )
        );

        list.forEach(l -> {
            t.put(l.name, WordVectorHelper.getSimilarity(this.wordMap.get(l.name), targetVector));
        });

        return list;
    }
}
