package com.zc.service;

import com.zc.bean.TopicAreaStatistics;
import java.util.List;

public interface TopicAreaStatisticsService {
    
    
    boolean add(TopicAreaStatistics record);
    
    boolean del(Integer id);

    boolean update(TopicAreaStatistics record);
    
    TopicAreaStatistics get(Integer id);


    List<TopicAreaStatistics> getColl(Integer top);

//    Page getCollByPage(Page page);

//    int delAll();

}