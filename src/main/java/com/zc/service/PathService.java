package com.zc.service;

import com.zc.model.path.NodeRelations;
import com.zc.model.path.PathModel;

import java.util.List;
import java.util.Map;

/**
 * Created by polun on 2016/8/16.
 */
public interface PathService {
    List<PathModel> getPaths(Integer topicId, String query);

    NodeRelations getRelations(String startNode, String endNode);

    Map<String, float[]> getWordVectorsCollByCache();
}
