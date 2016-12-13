package com.zc.dao;

import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * topictrend
 */
public interface TopicTrendMapper {

    List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id);

}