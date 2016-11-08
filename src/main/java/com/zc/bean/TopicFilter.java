package com.zc.bean;

/**
 * 
 */
public class TopicFilter {

    
    /**
     *  id ,所属表字段为TopicFilter.id
     */
    private Integer id;
    
    /**
     *  filterId ,所属表字段为TopicFilter.filter_id
     */
    private Integer filterId;
    
    /**
     *  topicId ,所属表字段为TopicFilter.topic_id
     */
    private Integer topicId;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 TopicFilter.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return TopicFilter.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  filterId 字段值
     * 
     * @param filterId
     *            将值赋予 TopicFilter.filter_id
     */
    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    /**
     * 获取  表的  filterId 字段
     * 
     * @return TopicFilter.filter_id
     */
    public Integer getFilterId() {
        return  filterId;
    }
    
    /**
     * 设置 表的  topicId 字段值
     * 
     * @param topicId
     *            将值赋予 TopicFilter.topic_id
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取  表的  topicId 字段
     * 
     * @return TopicFilter.topic_id
     */
    public Integer getTopicId() {
        return  topicId;
    }
    
    
}