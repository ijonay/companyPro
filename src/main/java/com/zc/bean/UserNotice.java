package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User_Notice
 */
public class UserNotice {

    
    /**
     * id id ,所属表字段为User_Notice.id
     */
    private Integer id;
    
    /**
     * notice_id noticeId ,所属表字段为User_Notice.notice_id
     */
    private Integer noticeId;
    
    /**
     * user_id userId ,所属表字段为User_Notice.user_id
     */
    private Integer userId;
    
    
    /**
     * 设置 User_Notice表的 id id 字段值
     * 
     * @param id
     *            将值赋予 User_Notice.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  User_Notice表的 id id 字段
     * 
     * @return User_Notice.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 User_Notice表的 notice_id noticeId 字段值
     * 
     * @param noticeId
     *            将值赋予 User_Notice.notice_id
     */
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * 获取  User_Notice表的 notice_id noticeId 字段
     * 
     * @return User_Notice.notice_id
     */
    public Integer getNoticeId() {
        return  noticeId;
    }
    
    /**
     * 设置 User_Notice表的 user_id userId 字段值
     * 
     * @param userId
     *            将值赋予 User_Notice.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取  User_Notice表的 user_id userId 字段
     * 
     * @return User_Notice.user_id
     */
    public Integer getUserId() {
        return  userId;
    }
    
    
}