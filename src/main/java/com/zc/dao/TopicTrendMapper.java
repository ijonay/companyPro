package com.zc.dao;

import com.zc.bean.TopicTrend;
import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * topictrend
 */
public interface TopicTrendMapper {


    int add(TopicTrend record);

    TopicTrend get(Integer id);

    int update(TopicTrend record);

    int del(Integer id);

    List<TopicTrend> getColl(@Param("top") Integer top);

    List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id);

}