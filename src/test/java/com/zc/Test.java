package com.zc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.jgrapht.Graph;
import org.jgrapht.alg.NeighborIndex;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 张镇强 on 2016/8/16 18:01.
 */
public class Test {
    private Stack<String> testPath = new Stack<>();
    private Set<String> testonPath = new HashSet<>();
    private List<Stack<String>> testpathList = new ArrayList<Stack<String>>();

    public static void main(String[] args) {
        String temp = "[-0.043273643, -0.022552885, 0.014952003, 0.06760047, 0.04724256, -0.035661884, -0.012260031, 0.010878087, -0.031092227, -0.0109199975, 0.0054246117, -0.026690993, -0.0036361073, -0.088428035, -0.014244156, -0.024067605, 0.0042233057, -0.027495975, -0.02823688, -0.07548669, -0.04116888, 7.1898196E-4, 0.019118039, 0.017411519, -0.084153906, -0.067324854, 0.015621694, 0.006789744, -0.12207826, -0.024575587, -0.01117107, 0.0013649389, -0.038070563, -0.0974351, -0.009001287, 0.12823784, 0.06434568, 0.03517963, 0.08529101, 0.035485256, -0.0014887812, 0.013785951, -8.317125E-4, -0.042108826, -0.06384534, 0.034708895, -0.009429252, 0.022883493, -0.028049542, -0.055317856, -0.08016047, 0.022365112, 0.02377909, -0.08891772, -0.00998268, 0.024471965, -0.003586717, 0.037750468, -0.018431855, -0.0818859, -0.041004047, 0.047901627, -0.016134933, 0.011661135, -0.07753142, 0.04396339, -0.09501088, -0.028634181, -0.00499187, 0.006086475, 0.03431032, -0.017169688, -4.7077687E-4, -0.08312131, 0.0012984194, 0.02999435, -0.005002011, 0.017590407, 0.015750568, -0.0034542507, 0.020260692, 0.0037096809, 0.047207575, -0.057951957, 0.019499375, -0.005626858, -0.050523564, -0.018094342, 0.078017406, -0.08667223, 0.05421561, 0.02985927, 0.085683346, -0.056126967, -0.02314244, -0.003499396, -0.0052314554, 0.017806435, 0.033681925, -0.039892234, 0.03711327, 0.0056341765, 0.03162312, -0.0070736227, 0.08521336, 0.044341695, -0.027961582, 0.006197438, -0.041255824, 0.011318918, 0.048467156, 0.013908686, -0.040596902, 0.02578852, -0.0038023344, -7.2089117E-4, -0.002559402, 0.009186564, 0.03291042, 0.03601135, 0.07352746, 0.011766995, 0.03575805, -0.052436758, -0.05403894, 0.038441427, 0.078767106, 0.0044571124, 0.006729225, 0.07293842, -0.061305452, -0.093875624, 0.076576345, 0.014511507, -0.0026297988, 0.04361932, -0.093502864, 0.01337614, 0.02319277, 0.06824401, -0.046306767, -0.0037474171, 0.05955002, 0.053527925, -0.014878962, -0.013424709, 0.031090308, 0.018385882, -0.010757645, 0.013551867, -0.0070817545, 0.0269175, -0.04068715, -0.010941245, 0.01818889, -0.050663114, 0.007986968, -0.059303515, 0.027059447, -0.04305593, -0.0332144, -0.04683486, -0.012410205, -0.030653493, 0.029636841, -0.060025733, -0.044314053, -0.026685433, 0.040699188, -0.12834558, -0.0044870474, 0.05623457, 0.0028061573, -0.08062653, -0.11851636, -0.046048477, 0.10381967, -0.0062614516, 0.060411632, -0.08020093, 0.02230212, -0.09145543, -0.066357225, -0.058727913, 0.06001523, 0.06814516, -0.0066864146, 0.059590008, 0.078928486, 0.05787709, -0.004620747, -0.060219582, -0.012477284, 9.900947E-4, 0.036122188, -0.008576997, -0.032902982, 0.041823387, 0.028999671, -0.01993852]";
        List<BigDecimal> a = (List<BigDecimal>) JSONArray.parse(temp);
        float[] b = new float[a.size()];
        for (int i = 0; i < a.size(); i++) {
            b[i] = a.get(i).floatValue();
        }
        System.exit(0);
//        List<Float> list = new ArrayList<>();
//        list.add(1.2f);
//        list.add(43.1f);
//        list.add(2.1f);
//        list.add(19.1f);
//        list.add(19.1f);
//
//        System.out.println(list);
//        Collections.sort(list, (left, right) -> CommonHelper.compare(left, right));


        SimpleWeightedGraph<String, DefaultWeightedEdge> G = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        G.addVertex("A");
        G.addVertex("B");
        G.addVertex("C");
        G.addVertex("D");
        G.addVertex("E");
        G.addVertex("F");
        G.addVertex("G");

        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("C", "F");
        G.addEdge("B", "F");
        G.addEdge("F", "D");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        Test test = new Test();
//        new AllPaths(null);
        List<List<String>> list = test.getAllPaths(G, "B", "F", null);
        System.out.println(list);
    }

    private void test(Graph graph, String start, String target) {
        // add node v to current path from s
        testPath.push(start);
        testonPath.add(start);

        // found path from s to t - currently prints in reverse order because of stack
        if (start.equals(target)) {
            Stack<String> temp = new Stack<>();
            temp.addAll(testPath);
            System.out.println(temp);
            System.out.println("------------------------");
            this.testpathList.add(temp);
        } else {
            NeighborIndex neighborIndex = new NeighborIndex(graph);
            List<String> neighbors = neighborIndex.neighborListOf(start);
            for (String w : neighbors) {
                if (!testonPath.contains(w)) test(graph, w, target);
            }
        }

        // done exploring from v, so remove from path
        testPath.pop();
        testonPath.remove(start);
    }


    public List<List<String>> getAllPaths(Graph graph, String start, String target, List<String> path) {
        if (path == null || path.size() < 1) {
            path = new ArrayList<>();
        }

        path.add(start);
        if (start.equals(target)) {
            List<List<String>> temp = new ArrayList<>();
            temp.add(path);
            return temp;
        }

        List<List<String>> allPaths = new ArrayList<>();
        NeighborIndex neighborIndex = new NeighborIndex(graph);
        List<String> neighbors = neighborIndex.neighborListOf(start);
        for (String n : neighbors) {
            if (!path.contains(n)) {
                List<List<String>> newPaths = getAllPaths(graph, n, target, path);

                for (List<String> p : newPaths) {
                    allPaths.add(p);
                }
            }
        }

        return allPaths;
    }
}
