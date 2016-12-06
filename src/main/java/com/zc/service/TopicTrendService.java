package com.zc.service;

import com.zc.bean.TopicTrend;
import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicTrendService {
    
    
    boolean add(TopicTrend record);
    
    boolean del(Integer id);

    boolean update(TopicTrend record);
    
    TopicTrend get(Integer id);

    List<TopicTrend> getColl(Integer top);

    List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id);
}