package com.zc.service;

import com.zc.dao.TopicTrendMapper;
import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicTrendServiceImpl implements TopicTrendService {

    @Autowired
    private TopicTrendMapper topicTrendMapper;

    @Override
    public List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id){
        return topicTrendMapper.getTopicTrendHistory(id);
    }
}
