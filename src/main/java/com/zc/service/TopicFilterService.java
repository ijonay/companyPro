package com.zc.service;

import com.zc.bean.TopicFilter;
import com.zc.bean.TopicFilterClass;
import com.zc.model.topicsearch.SearchModel;

import java.util.List;

public interface TopicFilterService {


    boolean add(TopicFilter record);

    boolean del(Integer id);

    boolean update(TopicFilter record);

    TopicFilter get(Integer id);

    List<Integer> getTopicIds(SearchModel searchModel);


    /**
     * 获取集合通过TopicId
     *
     * @param topicId
     * @return
     */
    List<TopicFilterClass> getByTopicIdAndType(Integer topicId, Integer type);
//    List<TopicFilter> getAll();
//
//    int delAll();

}