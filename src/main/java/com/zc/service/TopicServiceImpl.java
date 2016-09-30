/**
 * @title TopicServiceImpl.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午3:19:10
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.service;

import com.zc.bean.Topic;
import com.zc.dao.TopicDao;
import com.zc.model.TopicWordModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao dao;

    /**
     * 获取词汇与热点话题的相似度
     *
     * @param list
     * @return
     * @see com.zc.service.TopicService#getSimilarity(java.util.List)
     */
    public float getSimilarity(List<TopicWordModel> list) {
        if (list == null || list.size() == 0)
            return 0;
        float totalWeight = 0f;
        for (TopicWordModel model : list) {
            totalWeight += model.getWordSimilarity() * model.getTopicWeight();
        }
        return totalWeight / list.size();
    }

    /**
     * 方法说明
     *
     * @param topic
     * @return
     * @see com.zc.service.TopicService#add(com.zc.bean.Topic)
     */
    @Override
    public Integer add(Topic topic) {
        return dao.add(topic);
    }

    /**
     * 方法说明
     *
     * @param list
     * @return
     * @see com.zc.service.TopicService#batchInsert(java.util.List)
     */
    @Override
    public Integer batchInsert(List<Topic> list) {
        if (list == null || list.size() == 0) return 0;
        return dao.batchInsert(list);
    }

    /**
     * 方法说明
     * @param pageSize
     * @param currentPage
     * @return
     * @see com.zc.service.TopicService#getList(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<Topic> getList(Integer pageSize, Integer currentPage) {
       return dao.getList(pageSize, currentPage);
    }
    
    @Override
    public List<Topic> getTopicWordList(Integer pageSize, Integer currentPage) {
       return dao.getTopicWordList(pageSize, currentPage);
    }

    /**
     * 方法说明
     * @return
     * @see com.zc.service.TopicService#getItemCount()
     */
    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }

    /**
     * 方法说明
     * @param list
     * @return
     * @see com.zc.service.TopicService#batchUpdate(java.util.List)
     */
    @Override
    public Integer batchUpdate(List<Topic> list) {
        if(list==null||list.size()==0)return 0;
        return dao.batchUpdate(list);
    }
    
    @Override
    public Topic get(Integer topicId) {
        Topic topic = dao.get(topicId);
        Objects.requireNonNull(topic);

        return topic;
    }

}
