package com.zc.service;

import com.zc.bean.HotTopicPrediction;
import com.zc.model.HotTopicPredictionModel;

import java.util.List;

public interface HotTopicPredictionService {
    
    
    boolean add(HotTopicPrediction record);
    
    boolean del(Integer id);

    boolean update(HotTopicPrediction record);
    
    HotTopicPrediction get(Integer id);

    List<HotTopicPredictionModel> getHotTopicEventList(String dateStr);
}