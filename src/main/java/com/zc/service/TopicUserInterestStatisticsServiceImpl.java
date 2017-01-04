package com.zc.service;

import com.zc.bean.TopicUserInterestStatistics;
import com.zc.dao.TopicUserInterestStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicUserInterestStatisticsServiceImpl implements TopicUserInterestStatisticsService {

    @Autowired
    private TopicUserInterestStatisticsMapper topicUserInterestStatisticsMapper;

    @Override
    public boolean add(TopicUserInterestStatistics record) {
        return topicUserInterestStatisticsMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return topicUserInterestStatisticsMapper.del(id) > 0;
    }

    @Override
    public boolean update(TopicUserInterestStatistics record) {
        return topicUserInterestStatisticsMapper.update(record) > 0;
    }

    @Override
    public TopicUserInterestStatistics get(Integer id) {
        return topicUserInterestStatisticsMapper.get(id);
    }

    @Override
    public List<TopicUserInterestStatistics> getColl(Integer top) {
        return topicUserInterestStatisticsMapper.getColl(top);
    }
//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(topicUserInterestStatisticsMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return topicUserInterestStatisticsMapper.delAll(); }

}
