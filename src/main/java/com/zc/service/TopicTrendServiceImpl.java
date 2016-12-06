package com.zc.service;

import java.util.List;

import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zc.utility.page.Page;

import com.zc.bean.TopicTrend;
import com.zc.dao.TopicTrendMapper;

@Service
public class TopicTrendServiceImpl implements TopicTrendService {

    @Autowired
    private TopicTrendMapper topicTrendMapper;

    @Override
    public boolean add(TopicTrend record) {
        return topicTrendMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return topicTrendMapper.del(id) > 0;
    }

    @Override
    public boolean update(TopicTrend record) {
       return topicTrendMapper.update(record) > 0;
    }

    @Override
    public TopicTrend get(Integer id) {
        return topicTrendMapper.get(id);
    }
   @Override
   public List<TopicTrend> getColl(Integer top) {
       return topicTrendMapper.getColl(top);
   }

    @Override
    public List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id){
        return topicTrendMapper.getTopicTrendHistory(id);
    }
}
