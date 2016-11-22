package com.zc.dao;

import com.zc.bean.HotTopicPrediction;
import com.zc.model.HotTopicPredictionModel;

import java.util.List;

/**
 *
 */
public interface HotTopicPredictionMapper {

    int add(HotTopicPrediction record);

    HotTopicPrediction get(Integer id);

    int update(HotTopicPrediction record);

    int del(Integer id);

    List<HotTopicPredictionModel> getHotTopicEventList(String dateStr);

    List<HotTopicPrediction> getAll();


}