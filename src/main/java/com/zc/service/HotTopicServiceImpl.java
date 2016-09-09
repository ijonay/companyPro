package com.zc.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;






import org.springframework.stereotype.Service;

import com.zc.bean.HotTopic;
import com.zc.dao.HotTopicDao;
import com.zc.model.TopicWordModel;
@Service
public class HotTopicServiceImpl implements HotTopicService {
    @Autowired
    private HotTopicDao dao;

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
    public Integer add(HotTopic topic) {
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
    public Integer batchInsert(List<HotTopic> list) {
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
    public List<HotTopic> getList(Integer pageSize, Integer currentPage) {
       return dao.getList(pageSize, currentPage);
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
    public Integer batchUpdate(List<HotTopic> list) {
        if(list==null||list.size()==0)return 0;
        return dao.batchUpdate(list);
    }
    
    @Override
    public HotTopic get(Integer topicId) {
        HotTopic topic = dao.get(topicId);
        Objects.requireNonNull(topic);

        return topic;
    }

}
