package com.zc.model;

import java.util.Date;

/**
 * Created by zhangcl on 2016/11/9.
 */
public class HotTopicPredictionModel {

    private Integer id;
    private String name;
    private String startDate;
    private String endDate;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    private String  weiboUrl;
    private String  weiboTitle;
    private String  weiboReadNum;
    private String  weiboForwardNum;
    private String  weiboCommentsNum;
    private String  weiboLoveNum;
    private String  weixinTitle;
    private String  weixinUrl;
    private String  weixinReadNum;
    private String  zhihuTitle;
    private String  zhihuUrl;
    private String  zhihuAnswerNum;
    private String  baiduSearchKeyword;
    private String  baiduUrl;
    private String  baiduSearchNum;

    public String getWeiboTitle() {
        return weiboTitle;
    }

    public void setWeiboTitle(String weiboTitle) {
        this.weiboTitle = weiboTitle;
    }

    public String getWeiboReadNum() {
        return weiboReadNum;
    }

    public void setWeiboReadNum(String weiboReadNum) {
        this.weiboReadNum = weiboReadNum;
    }

    public String getWeiboForwardNum() {
        return weiboForwardNum;
    }

    public void setWeiboForwardNum(String weiboForwardNum) {
        this.weiboForwardNum = weiboForwardNum;
    }

    public String getWeiboCommentsNum() {
        return weiboCommentsNum;
    }

    public void setWeiboCommentsNum(String weiboCommentsNum) {
        this.weiboCommentsNum = weiboCommentsNum;
    }

    public String getWeiboLoveNum() {
        return weiboLoveNum;
    }

    public void setWeiboLoveNum(String weiboLoveNum) {
        this.weiboLoveNum = weiboLoveNum;
    }

    public String getWeixinTitle() {
        return weixinTitle;
    }

    public void setWeixinTitle(String weixinTitle) {
        this.weixinTitle = weixinTitle;
    }

    public String getWeixinUrl() {
        return weixinUrl;
    }

    public void setWeixinUrl(String weixinUrl) {
        this.weixinUrl = weixinUrl;
    }

    public String getWeixinReadNum() {
        return weixinReadNum;
    }

    public void setWeixinReadNum(String weixinReadNum) {
        this.weixinReadNum = weixinReadNum;
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

    public String getZhihuAnswerNum() {
        return zhihuAnswerNum;
    }

    public void setZhihuAnswerNum(String zhihuAnswerNum) {
        this.zhihuAnswerNum = zhihuAnswerNum;
    }

    public String getBaiduSearchKeyword() {
        return baiduSearchKeyword;
    }

    public void setBaiduSearchKeyword(String baiduSearchKeyword) {
        this.baiduSearchKeyword = baiduSearchKeyword;
    }

    public String getBaiduUrl() {
        return baiduUrl;
    }

    public void setBaiduUrl(String baiduUrl) {
        this.baiduUrl = baiduUrl;
    }

    public String getBaiduSearchNum() {
        return baiduSearchNum;
    }

    public void setBaiduSearchNum(String baiduSearchNum) {
        this.baiduSearchNum = baiduSearchNum;
    }
}
