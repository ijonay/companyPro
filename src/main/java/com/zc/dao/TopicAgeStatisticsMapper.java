package com.zc.dao;

import com.zc.bean.TopicAgeStatistics;

import java.util.List;

/**
 *
 */
public interface TopicAgeStatisticsMapper {


    int add(TopicAgeStatistics record);

    TopicAgeStatistics get(Integer id);

    int update(TopicAgeStatistics record);

    int del(Integer id);


    List<TopicAgeStatistics> getByTopicId(Integer topicId);

//    List<TopicAgeStatistics> getAll();
//
//    int delAll();

}