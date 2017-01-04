package com.zc.service;

import com.zc.bean.TopicUserInterestStatistics;
import java.util.List;

public interface TopicUserInterestStatisticsService {
    
    
    boolean add(TopicUserInterestStatistics record);
    
    boolean del(Integer id);

    boolean update(TopicUserInterestStatistics record);
    
    TopicUserInterestStatistics get(Integer id);


    List<TopicUserInterestStatistics> getColl(Integer top);

//    Page getCollByPage(Page page);

//    int delAll();

}