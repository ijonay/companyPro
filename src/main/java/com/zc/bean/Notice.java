package com.zc.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Notice
 */
public class Notice {

    
    /**
     * id id ,所属表字段为Notice.id
     */
    private Integer id;
    
    /**
     * name name ,所属表字段为Notice.name
     */
    private String name;
    
    /**
     * notice_type noticeType ,所属表字段为Notice.notice_type
     */
    private Integer noticeType;
    
    
    /**
     * 设置 Notice表的 id id 字段值
     * 
     * @param id
     *            将值赋予 Notice.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  Notice表的 id id 字段
     * 
     * @return Notice.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 Notice表的 name name 字段值
     * 
     * @param name
     *            将值赋予 Notice.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  Notice表的 name name 字段
     * 
     * @return Notice.name
     */
    public String getName() {
        return  name;
    }
    
    /**
     * 设置 Notice表的 notice_type noticeType 字段值
     * 
     * @param noticeType
     *            将值赋予 Notice.notice_type
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * 获取  Notice表的 notice_type noticeType 字段
     * 
     * @return Notice.notice_type
     */
    public Integer getNoticeType() {
        return  noticeType;
    }
    
    
}