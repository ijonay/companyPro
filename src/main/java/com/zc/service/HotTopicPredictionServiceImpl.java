package com.zc.service;

import java.util.List;

import com.zc.model.HotTopicPredictionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bean.HotTopicPrediction;
import com.zc.dao.HotTopicPredictionMapper;

@Service
public class HotTopicPredictionServiceImpl implements HotTopicPredictionService {

    @Autowired
    private HotTopicPredictionMapper hotTopicPredictionMapper;
    
    @Override
    public boolean add(HotTopicPrediction record) {
        return hotTopicPredictionMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return hotTopicPredictionMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(HotTopicPrediction record) {
       return hotTopicPredictionMapper.update(record) > 0;
    }

    @Override
    public HotTopicPrediction get(Integer id) {
        return hotTopicPredictionMapper.get(id);
    }

    @Override
    public List<HotTopicPredictionModel> getHotTopicEventList(String dateStr) {
        return hotTopicPredictionMapper.getHotTopicEventList(dateStr);
    }
}
