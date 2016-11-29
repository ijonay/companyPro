package com.zc.service;

import java.util.List;

import com.zc.bean.HotTopic;
import com.zc.model.TopicWordModel;

public interface  HotTopicService {
    float getSimilarity(List<TopicWordModel> list);
    Integer add(HotTopic topic);
    Integer batchInsert(List<HotTopic>list);
    List<HotTopic>getList(Integer pageSize,Integer rowStart);
    Integer getItemCount();
    Integer batchUpdate(List<HotTopic>list);
    HotTopic get(Integer topicId);
}
