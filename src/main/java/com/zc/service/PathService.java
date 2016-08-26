package com.zc.service;

import java.util.List;
import java.util.Stack;

/**
 * Created by polun on 2016/8/16.
 */
public interface PathService {
    List<Stack<String>> getPaths(Integer topicId, String query);
}
