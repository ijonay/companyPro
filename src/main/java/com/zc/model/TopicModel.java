/**
 * @title TopicModel.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月25日 下午8:42:45
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.model;

public class TopicModel {
    private Integer id;
    private String title;
    private Float score;
    private float[] coordinate;
    private String logoUrl;
    private String readNum;

    /**
     * 阅读量增长趋势 -1:下降,0:不变,1:上升
     */
    private Integer readNumTrendGrowth;
    private String relationDesc;
    private String topicUrl;
    private String topicType;
    private String introduction;
    private String logoImgUrl;
    private String logoImgUrlLocal;
    private String prevailingTrend;
    private String wechatUrl;
    private String wechatTitle;
    private Integer wechatAvgReadNum;
    private String baiduTitle;
    private String baiduUrl;
    private Integer baiduHitNum;
    private String zhihuTitle;
    private String zhihuUrl;
    private Integer zhihuAvgAnswerNumber;


    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the score
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * @return the coordinate
     */
    public float[] getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(float[] coordinate) {
        this.coordinate = coordinate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public Integer getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(Integer readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    public String getLogoImgUrlLocal() {
        return logoImgUrlLocal;
    }

    public void setLogoImgUrlLocal(String logoImgUrlLocal) {
        this.logoImgUrlLocal = logoImgUrlLocal;
    }

    public String getPrevailingTrend() {
        return prevailingTrend;
    }

    public void setPrevailingTrend(String prevailingTrend) {
        this.prevailingTrend = prevailingTrend;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    public String getWechatTitle() {
        return wechatTitle;
    }

    public void setWechatTitle(String wechatTitle) {
        this.wechatTitle = wechatTitle;
    }

    public Integer getWechatAvgReadNum() {
        return wechatAvgReadNum;
    }

    public void setWechatAvgReadNum(Integer wechatAvgReadNum) {
        this.wechatAvgReadNum = wechatAvgReadNum;
    }

    public String getBaiduTitle() {
        return baiduTitle;
    }

    public void setBaiduTitle(String baiduTitle) {
        this.baiduTitle = baiduTitle;
    }

    public String getBaiduUrl() {
        return baiduUrl;
    }

    public void setBaiduUrl(String baiduUrl) {
        this.baiduUrl = baiduUrl;
    }

    public Integer getBaiduHitNum() {
        return baiduHitNum;
    }

    public void setBaiduHitNum(Integer baiduHitNum) {
        this.baiduHitNum = baiduHitNum;
    }

    public String getZhihuTitle() {
        return zhihuTitle;
    }

    public void setZhihuTitle(String zhihuTitle) {
        this.zhihuTitle = zhihuTitle;
    }

    public String getZhihuUrl() {
        return zhihuUrl;
    }

    public void setZhihuUrl(String zhihuUrl) {
        this.zhihuUrl = zhihuUrl;
    }

    public Integer getZhihuAvgAnswerNumber() {
        return zhihuAvgAnswerNumber;
    }

    public void setZhihuAvgAnswerNumber(Integer zhihuAvgAnswerNumber) {
        this.zhihuAvgAnswerNumber = zhihuAvgAnswerNumber;
    }
}
