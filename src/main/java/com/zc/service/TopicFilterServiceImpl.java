package com.zc.service;

import com.zc.bean.TopicFilter;
import com.zc.bean.TopicFilterClass;
import com.zc.dao.TopicFilterMapper;
import com.zc.model.topicsearch.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicFilterServiceImpl implements TopicFilterService {

    @Autowired
    private TopicFilterMapper topicFilterMapper;

    @Override
    public boolean add(TopicFilter record) {
        return topicFilterMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return topicFilterMapper.del(id) > 0;
    }

    @Override
    public boolean update(TopicFilter record) {
        return topicFilterMapper.update(record) > 0;
    }

    @Override
    public TopicFilter get(Integer id) {
        return topicFilterMapper.get(id);
    }

    @Override
    public List<Integer> getTopicIds(SearchModel searchModel) {
        return topicFilterMapper.getTopicIds(searchModel);
    }

    @Override
    public List<TopicFilterClass> getByTopicIdAndType(Integer topicId, Integer type) {
        return topicFilterMapper.getByTopicIdAndType(topicId, type);
    }

//    @Override
//    public List<TopicFilter > getAll() { return topicFilterMapper.getAll();}
//
//    @Override
//    public int delAll() { return topicFilterMapper.delAll(); }

}
