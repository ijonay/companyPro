package com.zc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zc.bean.Topic;
import com.zc.model.WordRedisModel;
import com.zc.utility.CommonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

/**
 * Created by 张镇强 on 2016/8/16 16:02.
 */
@Service
public class PathServiceImpl implements PathService {
    @Autowired
    private TopicService topicService;
    @Autowired
    private RedisTemplate<String, WordRedisModel> redisTemplate;

    public List<Stack<String>> getPaths(Integer topicId, String query) {
        Topic topic = topicService.get(topicId);
        float[] topicVector = CommonHelper.stringToFloat(topic.getCoordinate());
        List<Stack<String>> paths = new AllPaths(redisTemplate).getAllPath(query, topicVector);

        return paths;
    }
}
