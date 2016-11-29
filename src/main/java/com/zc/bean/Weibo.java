package com.zc.bean;

import com.zc.model.WeiboModel;

public class Weibo {
    private Integer id;
    private Integer topicid;
    private String weiboContent;
    private String keyWords;
    private String coordinate;
    private String readNum;
    private byte readNumTrendGrowth;
    private String weiboUrl;

    public WeiboModel getModel() {
        WeiboModel model = new WeiboModel();
        model.setId(getId().longValue());
        model.setReadNum(getReadNum());
        model.setReadNumTrendGrowth(getReadNumTrendGrowth());
        model.setTopicid(getTopicid().longValue());
        model.setWeiboContent(getWeiboContent());
        model.setWeiboUrl(getWeiboUrl());
        return model;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
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

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public byte getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(byte readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }
}
