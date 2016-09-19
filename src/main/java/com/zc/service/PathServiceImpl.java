package com.zc.service;

import com.zc.bean.Topic;
import com.zc.model.WordRedisModel;
import com.zc.model.path.*;
import com.zc.utility.CommonHelper;
import com.zc.utility.Constant;
import com.zc.utility.PropertyHelper;
import com.zc.utility.WordVectorHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 张镇强 on 2016/8/16 16:02.
 */
@Service
public class PathServiceImpl implements PathService {
    //region autowired
    @Autowired
    private TopicService topicService;
    @Autowired
    private RedisTemplate<String, WordRedisModel> redisTemplate;
    @Autowired
    private WordService wordService;
    //endregion

    //region fields
    private final float SIMILARITY_THRESHOLD =
            Float.parseFloat(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.SIMILARITY_THRESHOLD));
    private final int TOPNSIZE =
            Integer.parseInt(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.TOP_NSIZE));
    private final float DISSIMILARITY_THRESHOLD =
            Float.parseFloat(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.DISSIMILARITY_THRESHOLD)); // 不相似阈值
    private int MAX_PATHLENGTH = Integer.parseInt(PropertyHelper.getValue(Constant.CONFIG_PROPERTIES, Constant.MAX_PATHLENGTH));
    private List<LinkedList<PathNode>> pathList;
    private boolean isFirst = true;
    private Stack<PathNode> path = new Stack<>();
    private Set<String> onPath = new HashSet<>();
    private float[] targetVector;
    //endregion

    public List<PathModel> getPaths(Integer topicId, String query) {
        this.pathList = new ArrayList<>();
        path = new Stack<>();
        onPath = new HashSet<>();

        Topic topic = topicService.get(topicId);
        float[] topicVector = CommonHelper.stringToFloat(topic.getCoordinate());
        List<PathModel> paths = getAllPath(query, topicVector);

        return paths;
    }

    @Override
    public NodeRelations getRelations(String startNode, String endNode) {

        return null;
    }

    //region helper method
    private List<PathModel> getAllPath(String start, float[] targetVector) {
        this.targetVector = targetVector;
        generatePath(new PathNode(start, null, 0));

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

    private void generatePath(PathNode start) {
        path.push(start);
        onPath.add(start.getName());

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

        path.pop();
        onPath.remove(start.getName());
    }

    private void runRecursion(String start, float[] targetVector) {
        Set<WordRedisModel> neighbors = redisTemplate.boundZSetOps(Constant.WORDR_EDISKEY_PREFIX + start).range(0, TOPNSIZE - 1);
        if (neighbors != null) {
            LinkedList<WordRedisModel> tempNeighbors = getSortedWordEntryList(neighbors, targetVector);
            for (WordRedisModel w : tempNeighbors) {
                if (!StringUtils.isEmpty(w.name) && !isDisSimilarity(start, w.name) && !onPath.contains(w.name)) {
                    float similarity = getSimilarity(start, w.getName());
                    generatePath(new PathNode(w.name, start, similarity));
                }
            }
        }
    }

    private boolean isDisSimilarity(String start, String target) {
        return getSimilarity(start, target) <= DISSIMILARITY_THRESHOLD;
    }

    private boolean isSatisfied(String start, float[] targetVector) {
        return getSimilarity(start, targetVector) >= SIMILARITY_THRESHOLD;
    }

    private float getSimilarity(String start, float[] targetVector) {
        float[] startVector = wordService.getModelMap().get(start);
        float similarity = WordVectorHelper.getSimilarity(startVector, targetVector);
        return similarity;
    }

    private float getSimilarity(String start, String target) {
        float[] startVector = wordService.getModelMap().get(start);
        float[] tVector = wordService.getModelMap().get(target);
        float similarity = WordVectorHelper.getSimilarity(startVector, tVector);

        return similarity;
    }

    private LinkedList<WordRedisModel> getSortedWordEntryList(Set<WordRedisModel> neighbors, float[] targetVector) {
        LinkedList<WordRedisModel> list = new LinkedList(neighbors);
        Collections.sort(list, (left, right) -> CommonHelper.compare(
                WordVectorHelper.getSimilarity(wordService.getModelMap().get(right.name), targetVector),
                WordVectorHelper.getSimilarity(wordService.getModelMap().get(left.name), targetVector)
                )
        );

        return list;
    }
    //endregion
}
