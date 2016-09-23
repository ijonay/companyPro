package com.zc.bean;

/**
 * Created by 张镇强 on 2016/9/18 12:43.
 */
public class Weibo {
    private Integer id;
    private Integer topicId;
    private String weiboContent;
    private String keywords;
    private String coordinate;
    private String readNum;
    private byte readNumTrendGrowth;
    private String weiboUrl;

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

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public byte getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(byte readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }
}
