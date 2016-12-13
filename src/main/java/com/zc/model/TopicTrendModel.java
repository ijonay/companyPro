package com.zc.model;

/**
 * Created by zhangcl on 2016/12/6.
 */
public class TopicTrendModel {

    private String id;
    private String createDate;
    private String prevailingTrend;

    public String getPrevailingTrend() {
        return prevailingTrend;
    }

    public void setPrevailingTrend(String prevailingTrend) {
        this.prevailingTrend = prevailingTrend;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
