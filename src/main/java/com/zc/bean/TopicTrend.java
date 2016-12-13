package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * topictrend
 */
public class TopicTrend {

    private Integer id;
    private Integer topicId;
    private Integer prevailingTrend;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPrevailingTrend() {
        return prevailingTrend;
    }

    public void setPrevailingTrend(Integer prevailingTrend) {
        this.prevailingTrend = prevailingTrend;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}