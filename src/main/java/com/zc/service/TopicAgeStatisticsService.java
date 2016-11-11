package com.zc.service;

import com.zc.bean.TopicAgeStatistics;

import java.util.List;

public interface TopicAgeStatisticsService {


    boolean add(TopicAgeStatistics record);

    boolean del(Integer id);

    boolean update(TopicAgeStatistics record);

    TopicAgeStatistics get(Integer id);

    //    List<TopicAgeStatistics> getAll();
//
//    int delAll();
    List<TopicAgeStatistics> getByTopicId(Integer topicId);
}