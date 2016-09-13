package com.zc.service;

import com.zc.model.path.PathModel;

import java.util.List;

/**
 * Created by polun on 2016/8/16.
 */
public interface PathService {
    List<PathModel> getPaths(Integer topicId, String query);
}
