package com.zc.model;

/**
 * Created by zhangcl on 2016/12/6.
 */
public class TopicTrendModel {

    private String id;
    private String insertDate;
    private String prevailingTrend;

    public String getPrevailingTrend() {
        return prevailingTrend;
    }

    public void setPrevailingTrend(String prevailingTrend) {
        this.prevailingTrend = prevailingTrend;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
