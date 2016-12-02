package com.zc.bean;

import java.util.Date;

/**
 * 
 */
public class VersionInfo {

    
    /**
     *  id ,所属表字段为version_info.id
     */
    private Integer id;
    
    /**
     *  name ,所属表字段为version_info.name
     */
    private String name;
    
    /**
     *  description ,所属表字段为version_info.description
     */
    private String description;
    
    /**
     *  version ,所属表字段为version_info.version
     */
    private String version;
    
    /**
     *  createTime ,所属表字段为version_info.create_time
     */
    private Date createTime;
    
    /**
     *  creator ,所属表字段为version_info.creator
     */
    private String creator;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 version_info.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return version_info.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  name 字段值
     * 
     * @param name
     *            将值赋予 version_info.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  表的  name 字段
     * 
     * @return version_info.name
     */
    public String getName() {
        return  name;
    }
    
    /**
     * 设置 表的  description 字段值
     * 
     * @param description
     *            将值赋予 version_info.description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取  表的  description 字段
     * 
     * @return version_info.description
     */
    public String getDescription() {
        return  description;
    }
    
    /**
     * 设置 表的  version 字段值
     * 
     * @param version
     *            将值赋予 version_info.version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取  表的  version 字段
     * 
     * @return version_info.version
     */
    public String getVersion() {
        return  version;
    }
    
    /**
     * 设置 表的  createTime 字段值
     * 
     * @param createTime
     *            将值赋予 version_info.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  表的  createTime 字段
     * 
     * @return version_info.create_time
     */
    public Date getCreateTime() {
        return  createTime;
    }
    
    /**
     * 设置 表的  creator 字段值
     * 
     * @param creator
     *            将值赋予 version_info.creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取  表的  creator 字段
     * 
     * @return version_info.creator
     */
    public String getCreator() {
        return  creator;
    }
    
    
}