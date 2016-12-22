package com.zc.dao;

import com.zc.bean.TopicUserInterestModel;
import com.zc.bean.TopicUserInterestStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TopicUserInterestStatistics
 */
public interface TopicUserInterestStatisticsMapper {


    int add(TopicUserInterestStatistics record);

    TopicUserInterestStatistics get(Integer id);

    int update(TopicUserInterestStatistics record);

    int del(Integer id);

    List<TopicUserInterestStatistics> getColl(@Param("top") Integer top);

    List<TopicUserInterestModel> getCollByTopicId(@Param("topicId") Integer topicId);

//    List<UserMessage> getByFields(@Param("topicId") Integer topicId
//    , @Param("classId") Integer classId
//    , @Param("scale") Integer scale
//    , @Param("percentage") Double percentage
//    , @Param("tgi") Double tgi
//    , @Param("interestStrength") Double interestStrength
//     );

//    List<TopicUserInterestStatistics> getCollByPage(Page page);

//    int delAll();

}