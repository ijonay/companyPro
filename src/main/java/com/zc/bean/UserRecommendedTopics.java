package com.zc.bean;

import java.util.Date;

/**
 * 
 */
public class UserRecommendedTopics {

    
    /**
     *  id ,所属表字段为UserRecommentedTopics.id
     */
    private Integer id;
    
    /**
     *  userId ,所属表字段为UserRecommentedTopics.user_id
     */
    private Integer userId;
    
    /**
     *  keyword ,所属表字段为UserRecommentedTopics.keyword
     */
    private String keyword;
    
    /**
     *  topicId ,所属表字段为UserRecommentedTopics.topic_id
     */
    private Integer topicId;
    
    /**
     *  createDate ,所属表字段为UserRecommentedTopics.create_date
     */
    private Date createDate;
    
    /**
     *  orderIndex ,所属表字段为UserRecommentedTopics.order_index
     */
    private Integer orderIndex;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 UserRecommentedTopics.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return UserRecommentedTopics.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  userId 字段值
     * 
     * @param userId
     *            将值赋予 UserRecommentedTopics.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取  表的  userId 字段
     * 
     * @return UserRecommentedTopics.user_id
     */
    public Integer getUserId() {
        return  userId;
    }
    
    /**
     * 设置 表的  keyword 字段值
     * 
     * @param keyword
     *            将值赋予 UserRecommentedTopics.keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取  表的  keyword 字段
     * 
     * @return UserRecommentedTopics.keyword
     */
    public String getKeyword() {
        return  keyword;
    }
    
    /**
     * 设置 表的  topicId 字段值
     * 
     * @param topicId
     *            将值赋予 UserRecommentedTopics.topic_id
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取  表的  topicId 字段
     * 
     * @return UserRecommentedTopics.topic_id
     */
    public Integer getTopicId() {
        return  topicId;
    }
    
    /**
     * 设置 表的  createDate 字段值
     * 
     * @param createDate
     *            将值赋予 UserRecommentedTopics.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取  表的  createDate 字段
     * 
     * @return UserRecommentedTopics.create_date
     */
    public Date getCreateDate() {
        return  createDate;
    }
    
    /**
     * 设置 表的  orderIndex 字段值
     * 
     * @param orderIndex
     *            将值赋予 UserRecommentedTopics.order_index
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * 获取  表的  orderIndex 字段
     * 
     * @return UserRecommentedTopics.order_index
     */
    public Integer getOrderIndex() {
        return  orderIndex;
    }
    
    
}