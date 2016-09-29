/** 
 * @title Weibo.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月23日 下午2:44:48 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.tempbean;

public class TopicCleanContent {
    private Integer topic_id;
    private Integer weibo_id;
    private String title;
    private String weibo_content_cleansed;
    private String comment_content_cleansed;

    /**
     * @return the weibo_content_cleansed
     */
    public String getWeibo_content_cleansed() {
        return weibo_content_cleansed;
    }

    /**
     * @param weibo_content_cleansed
     *            the weibo_content_cleansed to set
     */
    public void setWeibo_content_cleansed(String weibo_content_cleansed) {
        this.weibo_content_cleansed = weibo_content_cleansed;
    }

    /**
     * @return the comment_content_cleansed
     */
    public String getComment_content_cleansed() {
        return comment_content_cleansed;
    }

    /**
     * @param comment_content_cleansed
     *            the comment_content_cleansed to set
     */
    public void setComment_content_cleansed(String comment_content_cleansed) {
        this.comment_content_cleansed = comment_content_cleansed;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the topic_id
     */
    public Integer getTopic_id() {
        return topic_id;
    }

    /**
     * @param topic_id the topic_id to set
     */
    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public Integer getWeibo_id() {
        return weibo_id;
    }

    public void setWeibo_id(Integer weibo_id) {
        this.weibo_id = weibo_id;
    }
}
