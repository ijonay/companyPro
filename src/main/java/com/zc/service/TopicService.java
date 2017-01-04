/**
 * @title TopicService.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午3:13:19
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.service;

import com.zc.bean.Topic;
import com.zc.model.TopicModel;
import com.zc.model.TopicWordModel;
import com.zc.model.topicsearch.SearchModel;
import com.zc.utility.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TopicService {
    float getSimilarity(List<TopicWordModel> list);

    Integer add(Topic topic);

    Integer batchInsert(List<Topic> list);

    List<Topic> getList(Integer pageSize, Integer rowStart);

    List<Topic> getTopicWordList(Integer pageSize, Integer rowStart);

    Integer getItemCount();

    Integer batchUpdate(List<Topic> list);

    Topic get(Integer topicId);

    List<Topic> getHotTopic(String title, Integer count);

    List<Topic> getRandomHotTopic(Integer allCount, Integer count);

    HashMap<Integer, float[]> getAllCoordinates();

    /**
     * 从缓存中获取所有topic集合
     * zhuzhzh 2016年10月27日14:19:26
     *
     * @return
     */
    HashMap<Integer, float[]> getCoordinatesByCache();

    HashMap<Integer, float[]> getCoordinatesByTopicIdsAndCache(List<Integer> topicIds);


    Page getListExt(String clueWord, SearchModel searchModel, Integer currentPage, Integer pageSize);

    List<TopicModel> getTopicByIdList(List<Integer> idList, float[] sourceVectors);

    /**
     * 更新redis中的Topic向量数据集合到一个Key中
     */
    void cache_UpdateTopicVerctorToColl();

    /**
     * 更新redis中的Topic向量数据
     */
    void cache_UpdateTopicVerctors();


    Object getTopicPercentage(Integer topicId);

    boolean inactiveTopic(Integer id);

    boolean activeTopic(Integer id);

    /**
     * searching topics by keyword, keyword_title
     *
     * @param keyword
     * @param keyword_title
     * @return
     */
    List<TopicModel> getTopicsByKeyword(String keyword, String keywordTitle);

    boolean update(Topic topic);

    boolean applyManual(Integer id);

    boolean cancelManual(Integer id);

    int syncInsertTopic(Topic topic);

    ArrayList<TopicModel> getTopHotTopic(Map map);

    Topic getTopicByTitle(String title);

    List<String> getChildrenTopicNames(String topicName);

    List<String> getRepeatedWordList(List<String> tkwList, List<String> childrenTopicNames);

    List<String> getSimilarWords(List<String> tkwList, List<String> childrenTopicNames);


    List<Topic> getBySearchModel(SearchModel searchModel, Integer top);

    List<String> getTopicTitleKeywords(Integer topicId);

    List<String> getTopicNeighborWords(Topic topic, int count);

}
