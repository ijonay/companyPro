package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 */
public class Users {

    
    /**
     *  id ,所属表字段为users.id
     */
    private Integer id;
    
    /**
     *  userName ,所属表字段为users.username
     */
    private String userName;
    
    /**
     *  nickName ,所属表字段为users.nickname
     */
    private String nickName;
    
    /**
     *  password ,所属表字段为users.password
     */
    private String password;
    
    /**
     *  salt ,所属表字段为users.salt
     */
    private String salt;
    
    /**
     *  avatar ,所属表字段为users.avatar
     */
    private String avatar;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 users.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return users.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  userName 字段值
     * 
     * @param userName
     *            将值赋予 users.username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取  表的  userName 字段
     * 
     * @return users.username
     */
    public String getUserName() {
        return  userName;
    }
    
    /**
     * 设置 表的  nickName 字段值
     * 
     * @param nickName
     *            将值赋予 users.nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取  表的  nickName 字段
     * 
     * @return users.nickname
     */
    public String getNickName() {
        return  nickName;
    }
    
    /**
     * 设置 表的  password 字段值
     * 
     * @param password
     *            将值赋予 users.password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取  表的  password 字段
     * 
     * @return users.password
     */
    public String getPassword() {
        return  password;
    }
    
    /**
     * 设置 表的  salt 字段值
     * 
     * @param salt
     *            将值赋予 users.salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取  表的  salt 字段
     * 
     * @return users.salt
     */
    public String getSalt() {
        return  salt;
    }
    
    /**
     * 设置 表的  avatar 字段值
     * 
     * @param avatar
     *            将值赋予 users.avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取  表的  avatar 字段
     * 
     * @return users.avatar
     */
    public String getAvatar() {
        return  avatar;
    }
    
    
}