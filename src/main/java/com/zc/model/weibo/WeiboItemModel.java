package com.zc.model.weibo;

import com.zc.bean.Weibo;

/**
 * Created by 张镇强 on 2016/9/18 18:52.
 */
public class WeiboItemModel {
    private Integer id;
    private Integer topicId;
    private String weiboContent;
    private String keywords;
    private String coordinate;
    private String readNum;
    private byte readNumTrendGrowth;
    private String weiboUrl;

    public WeiboItemModel() {
    }

    public WeiboItemModel(Weibo entity) {
        this.fromEntity(entity);
    }

    public WeiboItemModel fromEntity(Weibo entity) {
        this.setId(entity.getId());
        this.setTopicId(entity.getTopicid());
        this.setWeiboContent(entity.getWeiboContent());
        this.setKeywords(entity.getKeyWords());
        this.setCoordinate(entity.getCoordinate());
        this.setReadNum(entity.getReadNum());
        this.setReadNumTrendGrowth(entity.getReadNumTrendGrowth());
        this.setWeiboUrl(entity.getWeiboUrl());

        return this;
    }

    public Weibo toEntity() {
        Weibo entity = new Weibo();
        entity.setId(this.getId());
        entity.setId(this.getId());
        entity.setTopicid(this.getTopicId());
        entity.setWeiboContent(this.getWeiboContent());
        entity.setKeyWords(this.getKeywords());
        entity.setCoordinate(this.getCoordinate());
        entity.setReadNum(this.getReadNum());
        entity.setReadNumTrendGrowth(this.getReadNumTrendGrowth());
        entity.setWeiboUrl(this.getWeiboUrl());

        return entity;
    }

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

    @Override
    public String toString() {
        return "WeiboItemModel{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", weiboContent='" + weiboContent + '\'' +
                ", keywords='" + keywords + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", readNum='" + readNum + '\'' +
                ", readNumTrendGrowth=" + readNumTrendGrowth +
                ", weiboUrl='" + weiboUrl + '\'' +
                '}';
    }
}
