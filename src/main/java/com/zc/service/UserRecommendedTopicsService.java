package com.zc.service;

import com.zc.bean.UserRecommendedTopics;
import com.zc.model.TopicModel;
import com.zc.model.UserRecommendedTopicModel;

import java.util.List;

public interface UserRecommendedTopicsService {
    
    
    boolean add(UserRecommendedTopics record);
    
    boolean del(Integer id);

    boolean update(UserRecommendedTopics record);
    
    UserRecommendedTopics get(Integer id);

    void updateUserRecommendedTopics();

    List<UserRecommendedTopicModel> getUserHotTopicMessageList(Integer userId,Integer count);

    TopicModel getTopicMessageDetail(Integer topicId);

}