package com.zc.service;

import com.zc.bean.TopicClass;
import com.zc.dao.TopicClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicClassServiceImpl implements TopicClassService {

    @Autowired
    private TopicClassMapper topicClassMapper;
    
    @Override
    public boolean add(TopicClass record) {
        return topicClassMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return topicClassMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(TopicClass record) {
       return topicClassMapper.update(record) > 0;
    }

    @Override
    public TopicClass get(Integer id) {
        return topicClassMapper.get(id);
    }

    @Override
    public List<TopicClass> getByParentId(Integer parentId) {
        return topicClassMapper.getByParentId(parentId);
    }

    @Override
    public List<TopicClass> getByType(Integer type) {
        return topicClassMapper.getByType(type);
    }
//    @Override
//    public List<TopicClass > getAll() { return topicClassMapper.getAll();}
//
//    @Override
//    public int delAll() { return topicClassMapper.delAll(); }

}
