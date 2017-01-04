package com.zc.dao;

import com.zc.bean.TopicAreaModel;
import com.zc.bean.TopicAreaStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TopicAreaStatistics
 */
public interface TopicAreaStatisticsMapper {


    int add(TopicAreaStatistics record);

    TopicAreaStatistics get(Integer id);

    int update(TopicAreaStatistics record);

    int del(Integer id);

    List<TopicAreaStatistics> getColl(@Param("top") Integer top);

    List<TopicAreaModel> getCollByTopicId(@Param("topicId") Integer topicId);

//    List<UserMessage> getByFields(@Param("topicId") Integer topicId
//    , @Param("classId") Integer classId
//    , @Param("scale") Integer scale
//    , @Param("percentage") Double percentage
//    , @Param("tgi") Double tgi
//     );

//    List<TopicAreaStatistics> getCollByPage(Page page);

//    int delAll();

}