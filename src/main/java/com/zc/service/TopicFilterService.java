package com.zc.service;

import com.zc.bean.TopicFilter;
import com.zc.model.topicsearch.SearchModel;

import java.util.List;

public interface TopicFilterService {
    
    
    boolean add(TopicFilter record);
    
    boolean del(Integer id);

    boolean update(TopicFilter record);
    
    TopicFilter get(Integer id);

    List<Integer> getTopicIds(SearchModel searchModel);

//    List<TopicFilter> getAll();
//
//    int delAll();

}