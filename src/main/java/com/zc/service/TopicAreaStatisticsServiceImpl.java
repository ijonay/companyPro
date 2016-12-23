package com.zc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zc.utility.page.Page;

import com.zc.bean.TopicAreaStatistics;
import com.zc.dao.TopicAreaStatisticsMapper;

@Service
public class TopicAreaStatisticsServiceImpl implements TopicAreaStatisticsService {

    @Autowired
    private TopicAreaStatisticsMapper topicAreaStatisticsMapper;

    @Override
    public boolean add(TopicAreaStatistics record) {
        return topicAreaStatisticsMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return topicAreaStatisticsMapper.del(id) > 0;
    }

    @Override
    public boolean update(TopicAreaStatistics record) {
       return topicAreaStatisticsMapper.update(record) > 0;
    }

    @Override
    public TopicAreaStatistics get(Integer id) {
        return topicAreaStatisticsMapper.get(id);
    }
   @Override
   public List<TopicAreaStatistics> getColl(Integer top) {
       return topicAreaStatisticsMapper.getColl(top);
   }
//  @Override
//  public Page getCollByPage(Page page) {
//
//  return page.data(topicAreaStatisticsMapper.getCollByPage(page));
//
//  }

//    @Override
//    public int delAll() { return topicAreaStatisticsMapper.delAll(); }

}
