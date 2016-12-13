package com.zc.service;

import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicTrendService {
    List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id);
}