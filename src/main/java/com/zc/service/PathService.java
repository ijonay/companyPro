package com.zc.service;

import com.zc.model.path.NodeRelations;
import com.zc.model.path.PathModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by polun on 2016/8/16.
 */
public interface PathService {
    List<PathModel> getPaths(Integer topicId, String query);

    NodeRelations getRelations(String startNode, String endNode);

    /**
     * 获取路径探索结果
     * zhuzhzh 2016年11月24日11:18:32
     *
     * @param start
     * @param end
     * @return
     */
    HashMap<String, Object> getPathSearch(String start, String end);


}
