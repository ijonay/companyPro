/**
 * @title TopicService.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午3:13:19
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.service;

import java.util.List;

import com.zc.bean.Topic;
import com.zc.model.TopicWordModel;

public interface TopicService {
    float getSimilarity(List<TopicWordModel> list);

    Integer add(Topic topic);

    Integer batchInsert(List<Topic> list);

    List<Topic> getList(Integer pageSize, Integer rowStart);

    Integer getItemCount();

    Integer batchUpdate(List<Topic> list);

    Topic get(Integer topicId);
}
