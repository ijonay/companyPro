/**
 * @title Topic.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月17日 下午12:05:31
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.dao;

import com.zc.bean.Topic;
import com.zc.model.TopicModel;
import com.zc.model.topicsearch.SearchModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TopicDao {
    Integer add(Topic topic);

    Integer del(Integer id);

    Integer update(Topic topic);

    Topic get(Integer id);

    List<Topic> getHotTopic(@Param("title") String title, @Param("count") Integer count);

    /**
     * 按热度进行排序后 从N条数据中随机抽选出M条
     *
     * @param allCount
     * @param count
     * @return
     */
    List<Topic> getRandomHotTopic(@Param("allCount") Integer allCount, @Param("count") Integer count);

    Integer batchInsert(@Param("list") List<Topic> list);

    List<Topic> getList(@Param("pageSize") Integer pageSize, @Param("rowStart") Integer rowStart);

    List<Topic> getTopicWordList(@Param("pageSize") Integer pageSize, @Param("rowStart") Integer rowStart);

    Integer getItemCount();

    Integer batchUpdate(@Param("list") List<Topic> list);

    List<HashMap<Integer, String>> getAllCoordinates();

    List<Topic> getByIdList(@Param("list") List<Integer> idList);

    int inactiveTopic(@Param("id") Integer id);

    int activeTopic(@Param("id") Integer id);

    List<TopicModel> getTopicsByKeyword(Map map);

    int applyManual(@Param("id") Integer id);

    int cancelManual(@Param("id") Integer id);

    int syncInsertTopic(Topic topic);

    ArrayList<TopicModel> getTopHotTopic(Map map);

    Topic getTopicByTitle(@Param("title") String title);

    List<String> getChildrenTopicNames(@Param("topicName") String topicName);

    String getTopicTitleKeywords(@Param("topicId") Integer topicId);

    List<Topic> getBySearchModel(@Param("searchModel") SearchModel searchModel, @Param("top") Integer top);

}
