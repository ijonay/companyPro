package com.zc.service;

import com.zc.bean.TopicAgeStatistics;
import com.zc.dao.TopicAgeStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicAgeStatisticsServiceImpl implements TopicAgeStatisticsService {

    @Autowired
    private TopicAgeStatisticsMapper topicAgeStatisticsMapper;

    @Override
    public boolean add(TopicAgeStatistics record) {
        return topicAgeStatisticsMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return topicAgeStatisticsMapper.del(id) > 0;
    }

    @Override
    public boolean update(TopicAgeStatistics record) {
        return topicAgeStatisticsMapper.update(record) > 0;
    }

    @Override
    public TopicAgeStatistics get(Integer id) {
        return topicAgeStatisticsMapper.get(id);
    }

    @Override
    public List<TopicAgeStatistics> getByTopicId(Integer topicId) {
        return topicAgeStatisticsMapper.getByTopicId(topicId);
    }
//    @Override
//    public List<TopicAgeStatistics > getAll() { return topicAgeStatisticsMapper.getAll();}
//
//    @Override
//    public int delAll() { return topicAgeStatisticsMapper.delAll(); }

}
