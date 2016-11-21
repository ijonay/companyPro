package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 */
public class HotTopicPrediction {

    
    /**
     *  id ,所属表字段为HotTopicPrediction.id
     */
    private Integer id;
    
    /**
     *  operatorUserId ,所属表字段为HotTopicPrediction.operator_user_id
     */
    private Integer operatorUserId;
    
    /**
     *  name ,所属表字段为HotTopicPrediction.name
     */
    private String name;
    
    /**
     *  startDate ,所属表字段为HotTopicPrediction.start_date
     */
    private Date startDate;
    
    /**
     *  createDate ,所属表字段为HotTopicPrediction.create_date
     */
    private Date createDate;
    
    /**
     *  endDate ,所属表字段为HotTopicPrediction.end_date
     */
    private Date endDate;
    
    /**
     *  desc ,所属表字段为HotTopicPrediction.desc
     */
    private String note;
    
    /**
     *  isActive ,所属表字段为HotTopicPrediction.is_active
     */
    private Integer isActive;
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

    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 HotTopicPrediction.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return HotTopicPrediction.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  operatorUserId 字段值
     * 
     * @param operatorUserId
     *            将值赋予 HotTopicPrediction.operator_user_id
     */
    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    /**
     * 获取  表的  operatorUserId 字段
     * 
     * @return HotTopicPrediction.operator_user_id
     */
    public Integer getOperatorUserId() {
        return  operatorUserId;
    }
    
    /**
     * 设置 表的  name 字段值
     * 
     * @param name
     *            将值赋予 HotTopicPrediction.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  表的  name 字段
     * 
     * @return HotTopicPrediction.name
     */
    public String getName() {
        return  name;
    }
    
    /**
     * 设置 表的  startDate 字段值
     * 
     * @param startDate
     *            将值赋予 HotTopicPrediction.start_date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取  表的  startDate 字段
     * 
     * @return HotTopicPrediction.start_date
     */
    public Date getStartDate() {
        return  startDate;
    }
    
    /**
     * 设置 表的  createDate 字段值
     * 
     * @param createDate
     *            将值赋予 HotTopicPrediction.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取  表的  createDate 字段
     * 
     * @return HotTopicPrediction.create_date
     */
    public Date getCreateDate() {
        return  createDate;
    }
    
    /**
     * 设置 表的  endDate 字段值
     * 
     * @param endDate
     *            将值赋予 HotTopicPrediction.end_date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取  表的  endDate 字段
     * 
     * @return HotTopicPrediction.end_date
     */
    public Date getEndDate() {
        return  endDate;
    }
    

    /**
     * 设置 表的  isActive 字段值
     * 
     * @param isActive
     *            将值赋予 HotTopicPrediction.is_active
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取  表的  isActive 字段
     * 
     * @return HotTopicPrediction.is_active
     */
    public Integer getIsActive() {
        return  isActive;
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