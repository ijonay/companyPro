package com.zc.dao;

import com.zc.bean.TopicFilter;
import com.zc.model.topicsearch.SearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface TopicFilterMapper {


    int add(TopicFilter record);

    TopicFilter get(Integer id);

    int update(TopicFilter record);

    int del(Integer id);

    /**
     * 获取TopicId集合
     *
     * @param searchModel
     * @return
     */
    List<Integer> getTopicIds(@Param("searchModel") SearchModel searchModel);

//    List<TopicFilter> getAll();
//
//    int delAll();

}