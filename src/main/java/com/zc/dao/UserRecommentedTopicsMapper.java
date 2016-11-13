package com.zc.dao;

import com.zc.bean.UserRecommendedTopics;
import com.zc.model.UserRecommendedTopicModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 */
public interface UserRecommentedTopicsMapper {

    int add(UserRecommendedTopics record);

    UserRecommendedTopics get(Integer id);

    int update(UserRecommendedTopics record);

    int del(Integer id);

    List<UserRecommendedTopicModel> getUserHotTopicMessageList(
            @Param("userId") Integer userId,
            @Param("count") Integer count);

    int delAll(Integer userId);

}