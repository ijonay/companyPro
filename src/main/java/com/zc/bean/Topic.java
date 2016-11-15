/**
 * @title Topic.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月17日 下午12:14:51
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.bean;

import com.zc.model.TopicModel;

import java.util.List;

public class Topic implements java.io.Serializable{


    /**
     * id ,所属表字段为topic.id
     */
    private Integer id;

    /**
     * title ,所属表字段为topic.title
     */
    private String title;

    /**
     * readNum ,所属表字段为topic.readnum
     */
    private String readNum;

    /**
     * topicUrl ,所属表字段为topic.topicurl
     */
    private String topicUrl;

    /**
     * keywords ,所属表字段为topic.keywords
     */
    private String keywords;

    /**
     * coordinate ,所属表字段为topic.coordinate
     */
    private String coordinate;

    /**
     * logo图片的url地址 logoUrl ,所属表字段为topic.logourl
     */
    private String logoUrl;

    /**
     * 阅读数增长趋势 -1:下降,0:不变,1:上升 readNumTrendGrowth ,所属表字段为topic.readnumtrendgrowth
     */
    private Integer readNumTrendGrowth;

    /**
     * 关联性描述 relationDesc ,所属表字段为topic.relationdesc
     */
    private String relationDesc;

    /**
     * topicType ,所属表字段为topic.topictype
     */
    private String topicType;
    /**
     * eventClass ,所属表字段为topic.event_class
     */
    private String eventClass;

    /**
     * introduction ,所属表字段为topic.introduction
     */
    private String introduction;

    /**
     * topicRegion ,所属表字段为topic.topic_region
     */
    private String topicRegion;

    /**
     * labels ,所属表字段为topic.labels
     */
    private String labels;

    /**
     * idBackup ,所属表字段为topic.id_backup
     */
    private Integer idBackup;

    /**
     * logoImgUrl ,所属表字段为topic.logo_img_url
     */
    private String logoImgUrl;

    /**
     * logoImgUrlLocal ,所属表字段为topic.logo_img_url_local
     */
    private String logoImgUrlLocal;

    /**
     * prevailingTrend ,所属表字段为topic.prevailing_trend
     */
    private String prevailingTrend;

    /**
     * wechatUrl ,所属表字段为topic.wechat_url
     */
    private String wechatUrl;

    /**
     * wechatTitle ,所属表字段为topic.wechat_title
     */
    private String wechatTitle;

    /**
     * wechatAvgReadNum ,所属表字段为topic.wechat_avg_read_num
     */
    private Integer wechatAvgReadNum;

    /**
     * baiduTitle ,所属表字段为topic.baidu_title
     */
    private String baiduTitle;

    /**
     * baiduUrl ,所属表字段为topic.baidu_url
     */
    private String baiduUrl;

    /**
     * baiduHitNum ,所属表字段为topic.baidu_hit_num
     */
    private Integer baiduHitNum;

    /**
     * zhihuTitle ,所属表字段为topic.zhihu_title
     */
    private String zhihuTitle;

    /**
     * zhihuUrl ,所属表字段为topic.zhihu_url
     */
    private String zhihuUrl;

    /**
     * zhihuAvgAnswerNumber ,所属表字段为topic.zhihu_avg_answer_number
     */
    private Integer zhihuAvgAnswerNumber;


    /**
     * 设置 表的  id 字段值
     *
     * @param id 将值赋予 topic.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     *
     * @return topic.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 表的  title 字段值
     *
     * @param title 将值赋予 topic.title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取  表的  title 字段
     *
     * @return topic.title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 表的  readNum 字段值
     *
     * @param readNum 将值赋予 topic.readnum
     */
    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    /**
     * 获取  表的  readNum 字段
     *
     * @return topic.readnum
     */
    public String getReadNum() {
        return readNum;
    }

    /**
     * 设置 表的  topicUrl 字段值
     *
     * @param topicUrl 将值赋予 topic.topicurl
     */
    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    /**
     * 获取  表的  topicUrl 字段
     *
     * @return topic.topicurl
     */
    public String getTopicUrl() {
        return topicUrl;
    }

    /**
     * 设置 表的  keywords 字段值
     *
     * @param keywords 将值赋予 topic.keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取  表的  keywords 字段
     *
     * @return topic.keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置 表的  coordinate 字段值
     *
     * @param coordinate 将值赋予 topic.coordinate
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * 获取  表的  coordinate 字段
     *
     * @return topic.coordinate
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * 设置 表的 logo图片的url地址 logoUrl 字段值
     *
     * @param logoUrl 将值赋予 topic.logourl
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    /**
     * 获取  表的 logo图片的url地址 logoUrl 字段
     *
     * @return topic.logourl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置 表的 阅读数增长趋势 -1:下降,0:不变,1:上升 readNumTrendGrowth 字段值
     *
     * @param readNumTrendGrowth 将值赋予 topic.readnumtrendgrowth
     */
    public void setReadNumTrendGrowth(Integer readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

    /**
     * 获取  表的 阅读数增长趋势 -1:下降,0:不变,1:上升 readNumTrendGrowth 字段
     *
     * @return topic.readnumtrendgrowth
     */
    public Integer getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    /**
     * 设置 表的 关联性描述 relationDesc 字段值
     *
     * @param relationDesc 将值赋予 topic.relationdesc
     */
    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    /**
     * 获取  表的 关联性描述 relationDesc 字段
     *
     * @return topic.relationdesc
     */
    public String getRelationDesc() {
        return relationDesc;
    }

    /**
     * 设置 表的  topicType 字段值
     *
     * @param topicType 将值赋予 topic.topictype
     */
    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    /**
     * 获取  表的  topicType 字段
     *
     * @return topic.topictype
     */
    public String getTopicType() {
        return topicType;
    }

    /**
     * 设置 表的  eventClass 字段值
     *
     * @param eventClass 将值赋予 topic.event_class
     */
    public void setEventClass(String eventClass) {
        this.eventClass = eventClass;
    }

    /**
     * 获取  表的  eventClass 字段
     *
     * @return topic.event_class
     */
    public String getEventClass() {
        return eventClass;
    }

    /**
     * 设置 表的  introduction 字段值
     *
     * @param introduction 将值赋予 topic.introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * 获取  表的  introduction 字段
     *
     * @return topic.introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置 表的  topicRegion 字段值
     *
     * @param topicRegion 将值赋予 topic.topic_region
     */
    public void setTopicRegion(String topicRegion) {
        this.topicRegion = topicRegion;
    }

    /**
     * 获取  表的  topicRegion 字段
     *
     * @return topic.topic_region
     */
    public String getTopicRegion() {
        return topicRegion;
    }

    /**
     * 设置 表的  labels 字段值
     *
     * @param labels 将值赋予 topic.labels
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }

    /**
     * 获取  表的  labels 字段
     *
     * @return topic.labels
     */
    public String getLabels() {
        return labels;
    }

    /**
     * 设置 表的  idBackup 字段值
     *
     * @param idBackup 将值赋予 topic.id_backup
     */
    public void setIdBackup(Integer idBackup) {
        this.idBackup = idBackup;
    }

    /**
     * 获取  表的  idBackup 字段
     *
     * @return topic.id_backup
     */
    public Integer getIdBackup() {
        return idBackup;
    }

    /**
     * 设置 表的  logoImgUrl 字段值
     *
     * @param logoImgUrl 将值赋予 topic.logo_img_url
     */
    public void setLogoImgUrl(String logoImgUrl) {
        this.logoImgUrl = logoImgUrl;
    }

    /**
     * 获取  表的  logoImgUrl 字段
     *
     * @return topic.logo_img_url
     */
    public String getLogoImgUrl() {
        return logoImgUrl;
    }

    /**
     * 设置 表的  logoImgUrlLocal 字段值
     *
     * @param logoImgUrlLocal 将值赋予 topic.logo_img_url_local
     */
    public void setLogoImgUrlLocal(String logoImgUrlLocal) {
        this.logoImgUrlLocal = logoImgUrlLocal;
    }

    /**
     * 获取  表的  logoImgUrlLocal 字段
     *
     * @return topic.logo_img_url_local
     */
    public String getLogoImgUrlLocal() {
        return logoImgUrlLocal;
    }

    /**
     * 设置 表的  prevailingTrend 字段值
     *
     * @param prevailingTrend 将值赋予 topic.prevailing_trend
     */
    public void setPrevailingTrend(String prevailingTrend) {
        this.prevailingTrend = prevailingTrend;
    }

    /**
     * 获取  表的  prevailingTrend 字段
     *
     * @return topic.prevailing_trend
     */
    public String getPrevailingTrend() {
        return prevailingTrend;
    }

    /**
     * 设置 表的  wechatUrl 字段值
     *
     * @param wechatUrl 将值赋予 topic.wechat_url
     */
    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    /**
     * 获取  表的  wechatUrl 字段
     *
     * @return topic.wechat_url
     */
    public String getWechatUrl() {
        return wechatUrl;
    }

    /**
     * 设置 表的  wechatTitle 字段值
     *
     * @param wechatTitle 将值赋予 topic.wechat_title
     */
    public void setWechatTitle(String wechatTitle) {
        this.wechatTitle = wechatTitle;
    }

    /**
     * 获取  表的  wechatTitle 字段
     *
     * @return topic.wechat_title
     */
    public String getWechatTitle() {
        return wechatTitle;
    }

    /**
     * 设置 表的  wechatAvgReadNum 字段值
     *
     * @param wechatAvgReadNum 将值赋予 topic.wechat_avg_read_num
     */
    public void setWechatAvgReadNum(Integer wechatAvgReadNum) {
        this.wechatAvgReadNum = wechatAvgReadNum;
    }

    /**
     * 获取  表的  wechatAvgReadNum 字段
     *
     * @return topic.wechat_avg_read_num
     */
    public Integer getWechatAvgReadNum() {
        return wechatAvgReadNum;
    }

    /**
     * 设置 表的  baiduTitle 字段值
     *
     * @param baiduTitle 将值赋予 topic.baidu_title
     */
    public void setBaiduTitle(String baiduTitle) {
        this.baiduTitle = baiduTitle;
    }

    /**
     * 获取  表的  baiduTitle 字段
     *
     * @return topic.baidu_title
     */
    public String getBaiduTitle() {
        return baiduTitle;
    }

    /**
     * 设置 表的  baiduUrl 字段值
     *
     * @param baiduUrl 将值赋予 topic.baidu_url
     */
    public void setBaiduUrl(String baiduUrl) {
        this.baiduUrl = baiduUrl;
    }

    /**
     * 获取  表的  baiduUrl 字段
     *
     * @return topic.baidu_url
     */
    public String getBaiduUrl() {
        return baiduUrl;
    }

    /**
     * 设置 表的  baiduHitNum 字段值
     *
     * @param baiduHitNum 将值赋予 topic.baidu_hit_num
     */
    public void setBaiduHitNum(Integer baiduHitNum) {
        this.baiduHitNum = baiduHitNum;
    }

    /**
     * 获取  表的  baiduHitNum 字段
     *
     * @return topic.baidu_hit_num
     */
    public Integer getBaiduHitNum() {
        return baiduHitNum;
    }

    /**
     * 设置 表的  zhihuTitle 字段值
     *
     * @param zhihuTitle 将值赋予 topic.zhihu_title
     */
    public void setZhihuTitle(String zhihuTitle) {
        this.zhihuTitle = zhihuTitle;
    }

    /**
     * 获取  表的  zhihuTitle 字段
     *
     * @return topic.zhihu_title
     */
    public String getZhihuTitle() {
        return zhihuTitle;
    }

    /**
     * 设置 表的  zhihuUrl 字段值
     *
     * @param zhihuUrl 将值赋予 topic.zhihu_url
     */
    public void setZhihuUrl(String zhihuUrl) {
        this.zhihuUrl = zhihuUrl;
    }

    /**
     * 获取  表的  zhihuUrl 字段
     *
     * @return topic.zhihu_url
     */
    public String getZhihuUrl() {
        return zhihuUrl;
    }

    /**
     * 设置 表的  zhihuAvgAnswerNumber 字段值
     *
     * @param zhihuAvgAnswerNumber 将值赋予 topic.zhihu_avg_answer_number
     */
    public void setZhihuAvgAnswerNumber(Integer zhihuAvgAnswerNumber) {
        this.zhihuAvgAnswerNumber = zhihuAvgAnswerNumber;
    }

    /**
     * 获取  表的  zhihuAvgAnswerNumber 字段
     *
     * @return topic.zhihu_avg_answer_number
     */
    public Integer getZhihuAvgAnswerNumber() {
        return zhihuAvgAnswerNumber;
    }


    private List<WordDataRelations> words;

    private Integer isActie;

    public TopicModel getModel() {

        TopicModel model = new TopicModel();

        model.setId(id);
        model.setTitle(title);
        model.setLogoUrl(logoUrl);
        model.setReadNum(readNum);
        model.setReadNumTrendGrowth(getReadNumTrendGrowth());
        model.setRelationDesc(relationDesc);
        model.setTitle(title);
        model.setTopicType(topicType);
        model.setTopicUrl(topicUrl);
        model.setIntroduction(introduction);

        model.setLogoImgUrl(logoImgUrl);
        model.setLogoImgUrlLocal(logoImgUrlLocal);
        model.setPrevailingTrend(prevailingTrend);

        model.setBaiduHitNum(baiduHitNum);
        model.setBaiduTitle(baiduTitle);
        model.setBaiduUrl(baiduUrl);

        model.setZhihuAvgAnswerNumber(zhihuAvgAnswerNumber);
        model.setZhihuTitle(zhihuTitle);
        model.setZhihuUrl(zhihuUrl);

        model.setWechatAvgReadNum(wechatAvgReadNum);
        model.setWechatTitle(wechatTitle);
        model.setWechatUrl(wechatUrl);

        model.setEventClass(eventClass);

        model.setIsActive(isActie);
        return model;
    }


    public List<WordDataRelations> getWords() {
        return words;
    }

    public void setWords(List<WordDataRelations> words) {
        this.words = words;
    }

    public Integer getIsActie() {
        return isActie;
    }

    public void setIsActie(Integer isActie) {
        this.isActie = isActie;
    }
}
