package com.zc.service;

import com.zc.model.path.PathNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by polun on 2016/8/16.
 */
public interface PathService {
    List<LinkedList<PathNode>> getPaths(Integer topicId, String query);
}
