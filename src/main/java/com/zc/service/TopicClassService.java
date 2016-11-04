package com.zc.service;

import com.zc.bean.TopicClass;

import java.util.List;

public interface TopicClassService {
    
    
    boolean add(TopicClass record);
    
    boolean del(Integer id);

    boolean update(TopicClass record);
    
    TopicClass get(Integer id);

    List<TopicClass> getByParentId(Integer parentId);

    List<TopicClass> getByType(Integer type);



//    List<TopicClass> getAll();
//
//    int delAll();

}