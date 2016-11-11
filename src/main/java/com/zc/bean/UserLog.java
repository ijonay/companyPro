package com.zc.bean;

import java.util.Date;

/**
 * 
 */
public class UserLog {

    
    /**
     *  id ,所属表字段为UserLog.id
     */
    private Integer id;
    
    /**
     *  userId ,所属表字段为UserLog.user_id
     */
    private Integer userId;
    
    /**
     *  logType ,所属表字段为UserLog.log_type
     */
    private Integer logType;
    
    /**
     *  logInfo ,所属表字段为UserLog.log_info
     */
    private String logInfo;
    
    /**
     *  createDate ,所属表字段为UserLog.create_date
     */
    private Date createDate;
    
    /**
     *  ip ,所属表字段为UserLog.ip
     */
    private String ip;
    
    /**
     *  exploreType ,所属表字段为UserLog.explore_type
     */
    private String exploreType;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 UserLog.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return UserLog.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  userId 字段值
     * 
     * @param userId
     *            将值赋予 UserLog.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取  表的  userId 字段
     * 
     * @return UserLog.user_id
     */
    public Integer getUserId() {
        return  userId;
    }
    
    /**
     * 设置 表的  logType 字段值
     * 
     * @param logType
     *            将值赋予 UserLog.log_type
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     * 获取  表的  logType 字段
     * 
     * @return UserLog.log_type
     */
    public Integer getLogType() {
        return  logType;
    }
    
    /**
     * 设置 表的  logInfo 字段值
     * 
     * @param logInfo
     *            将值赋予 UserLog.log_info
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    /**
     * 获取  表的  logInfo 字段
     * 
     * @return UserLog.log_info
     */
    public String getLogInfo() {
        return  logInfo;
    }
    
    /**
     * 设置 表的  createDate 字段值
     * 
     * @param createDate
     *            将值赋予 UserLog.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取  表的  createDate 字段
     * 
     * @return UserLog.create_date
     */
    public Date getCreateDate() {
        return  createDate;
    }
    
    /**
     * 设置 表的  ip 字段值
     * 
     * @param ip
     *            将值赋予 UserLog.ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取  表的  ip 字段
     * 
     * @return UserLog.ip
     */
    public String getIp() {
        return  ip;
    }
    
    /**
     * 设置 表的  exploreType 字段值
     * 
     * @param exploreType
     *            将值赋予 UserLog.explore_type
     */
    public void setExploreType(String exploreType) {
        this.exploreType = exploreType;
    }

    /**
     * 获取  表的  exploreType 字段
     * 
     * @return UserLog.explore_type
     */
    public String getExploreType() {
        return  exploreType;
    }
    
    
}