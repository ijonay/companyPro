package com.zc.service;

import com.zc.model.WordRedisModel;

import java.util.List;

/**
 * Created by polun on 2016/8/29.
 */
public interface RedisService {
    void add();

    void get(String word);
}
