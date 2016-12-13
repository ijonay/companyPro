package com.zc.bean;

import java.util.Date;

/**
 * Message
 */
public class Message {


    /**
     * id id ,所属表字段为Message.id
     */
    private Integer id;

    /**
     * sender sender ,所属表字段为Message.sender
     */
    private String sender;

    /**
     * receiver receiver ,所属表字段为Message.receiver
     */
    private String receiver;

    /**
     * type type ,所属表字段为Message.type
     */
    private Integer type;

    /**
     * create_time createTime ,所属表字段为Message.create_time
     */
    private Date createTime;

    /**
     * expred_time expredTime ,所属表字段为Message.expred_time
     */
    private Date expredTime;

    /**
     * title title ,所属表字段为Message.title
     */
    private String title;

    /**
     * content content ,所属表字段为Message.content
     */
    private String content;

    /**
     * trigger_id triggerId ,所属表字段为Message.trigger_id
     */
    private Integer triggerId;


    /**
     * 设置 Message表的 id id 字段值
     *
     * @param id
     *            将值赋予 Message.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  Message表的 id id 字段
     *
     * @return Message.id
     */
    public Integer getId() {
        return  id;
    }

    /**
     * 设置 Message表的 sender sender 字段值
     *
     * @param sender
     *            将值赋予 Message.sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 获取  Message表的 sender sender 字段
     *
     * @return Message.sender
     */
    public String getSender() {
        return  sender;
    }

    /**
     * 设置 Message表的 receiver receiver 字段值
     *
     * @param receiver
     *            将值赋予 Message.receiver
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取  Message表的 receiver receiver 字段
     *
     * @return Message.receiver
     */
    public String getReceiver() {
        return  receiver;
    }

    /**
     * 设置 Message表的 type type 字段值
     *
     * @param type
     *            将值赋予 Message.type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取  Message表的 type type 字段
     *
     * @return Message.type
     */
    public Integer getType() {
        return  type;
    }

    /**
     * 设置 Message表的 create_time createTime 字段值
     *
     * @param createTime
     *            将值赋予 Message.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  Message表的 create_time createTime 字段
     *
     * @return Message.create_time
     */
    public Date getCreateTime() {
        return  createTime;
    }

    /**
     * 设置 Message表的 expred_time expredTime 字段值
     *
     * @param expredTime
     *            将值赋予 Message.expred_time
     */
    public void setExpredTime(Date expredTime) {
        this.expredTime = expredTime;
    }

    /**
     * 获取  Message表的 expred_time expredTime 字段
     *
     * @return Message.expred_time
     */
    public Date getExpredTime() {
        return  expredTime;
    }

    /**
     * 设置 Message表的 title title 字段值
     *
     * @param title
     *            将值赋予 Message.title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取  Message表的 title title 字段
     *
     * @return Message.title
     */
    public String getTitle() {
        return  title;
    }

    /**
     * 设置 Message表的 content content 字段值
     *
     * @param content
     *            将值赋予 Message.content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取  Message表的 content content 字段
     *
     * @return Message.content
     */
    public String getContent() {
        return  content;
    }

    /**
     * 设置 Message表的 trigger_id triggerId 字段值
     *
     * @param triggerId
     *            将值赋予 Message.trigger_id
     */
    public void setTriggerId(Integer triggerId) {
        this.triggerId = triggerId;
    }

    /**
     * 获取  Message表的 trigger_id triggerId 字段
     *
     * @return Message.trigger_id
     */
    public Integer getTriggerId() {
        return  triggerId;
    }


}