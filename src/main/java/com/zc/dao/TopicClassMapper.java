package com.zc.dao;

import com.zc.bean.TopicClass;

import java.util.List;

/**
 *
 */
public interface TopicClassMapper {


    int add(TopicClass record);

    TopicClass get(Integer id);

    int update(TopicClass record);

    int del(Integer id);

    List<TopicClass> getByParentId(Integer parentId);

    List<TopicClass> getByType(Integer type);

//    List<TopicClass> getAll();
//
//    int delAll();

}