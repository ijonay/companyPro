package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User_Message
 */
public class UserMessage {

    
    /**
     * id id ,所属表字段为User_Message.id
     */
    private Integer id;
    
    /**
     * message_id messageId ,所属表字段为User_Message.message_id
     */
    private Integer messageId;
    
    /**
     * user_id userId ,所属表字段为User_Message.user_id
     */
    private Integer userId;
    
    /**
     * create_time createTime ,所属表字段为User_Message.create_time
     */
    private Date createTime;
    
    /**
     * read_time readTime ,所属表字段为User_Message.read_time
     */
    private Date readTime;
    
    
    /**
     * 设置 User_Message表的 id id 字段值
     * 
     * @param id
     *            将值赋予 User_Message.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  User_Message表的 id id 字段
     * 
     * @return User_Message.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 User_Message表的 message_id messageId 字段值
     * 
     * @param messageId
     *            将值赋予 User_Message.message_id
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取  User_Message表的 message_id messageId 字段
     * 
     * @return User_Message.message_id
     */
    public Integer getMessageId() {
        return  messageId;
    }
    
    /**
     * 设置 User_Message表的 user_id userId 字段值
     * 
     * @param userId
     *            将值赋予 User_Message.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取  User_Message表的 user_id userId 字段
     * 
     * @return User_Message.user_id
     */
    public Integer getUserId() {
        return  userId;
    }
    
    /**
     * 设置 User_Message表的 create_time createTime 字段值
     * 
     * @param createTime
     *            将值赋予 User_Message.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  User_Message表的 create_time createTime 字段
     * 
     * @return User_Message.create_time
     */
    public Date getCreateTime() {
        return  createTime;
    }
    
    /**
     * 设置 User_Message表的 read_time readTime 字段值
     * 
     * @param readTime
     *            将值赋予 User_Message.read_time
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * 获取  User_Message表的 read_time readTime 字段
     * 
     * @return User_Message.read_time
     */
    public Date getReadTime() {
        return  readTime;
    }
    
    
}