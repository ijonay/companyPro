package com.zc.bean;

import java.util.Date;

/**
 * users
 */
public class Users {


    /**
     * id id ,所属表字段为users.id
     */
    private Integer id;

    /**
     * username userName ,所属表字段为users.username
     */
    private String userName;

    /**
     * nickname nickName ,所属表字段为users.nickname
     */
    private String nickName;

    /**
     * password password ,所属表字段为users.password
     */
    private String password;

    /**
     * salt salt ,所属表字段为users.salt
     */
    private String salt;

    /**
     * avatar avatar ,所属表字段为users.avatar
     */
    private String avatar;

    /**
     * company company ,所属表字段为users.company
     */
    private String company;

    /**
     * role role ,所属表字段为users.role
     */
    private String role;

    /**
     * isactive isactive ,所属表字段为users.isactive
     */
    private Boolean isactive;

    /**
     * create_time createTime ,所属表字段为users.create_time
     */
    private Date createTime;


    /**
     * 设置 users表的 id id 字段值
     *
     * @param id
     *            将值赋予 users.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  users表的 id id 字段
     *
     * @return users.id
     */
    public Integer getId() {
        return  id;
    }

    /**
     * 设置 users表的 username userName 字段值
     *
     * @param userName
     *            将值赋予 users.username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取  users表的 username userName 字段
     *
     * @return users.username
     */
    public String getUserName() {
        return  userName;
    }

    /**
     * 设置 users表的 nickname nickName 字段值
     *
     * @param nickName
     *            将值赋予 users.nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取  users表的 nickname nickName 字段
     *
     * @return users.nickname
     */
    public String getNickName() {
        return  nickName;
    }

    /**
     * 设置 users表的 password password 字段值
     *
     * @param password
     *            将值赋予 users.password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取  users表的 password password 字段
     *
     * @return users.password
     */
    public String getPassword() {
        return  password;
    }

    /**
     * 设置 users表的 salt salt 字段值
     *
     * @param salt
     *            将值赋予 users.salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取  users表的 salt salt 字段
     *
     * @return users.salt
     */
    public String getSalt() {
        return  salt;
    }

    /**
     * 设置 users表的 avatar avatar 字段值
     *
     * @param avatar
     *            将值赋予 users.avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取  users表的 avatar avatar 字段
     *
     * @return users.avatar
     */
    public String getAvatar() {
        return  avatar;
    }

    /**
     * 设置 users表的 company company 字段值
     *
     * @param company
     *            将值赋予 users.company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取  users表的 company company 字段
     *
     * @return users.company
     */
    public String getCompany() {
        return  company;
    }

    /**
     * 设置 users表的 role role 字段值
     *
     * @param role
     *            将值赋予 users.role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取  users表的 role role 字段
     *
     * @return users.role
     */
    public String getRole() {
        return  role;
    }

    /**
     * 设置 users表的 isactive isactive 字段值
     *
     * @param isactive
     *            将值赋予 users.isactive
     */
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    /**
     * 获取  users表的 isactive isactive 字段
     *
     * @return users.isactive
     */
    public Boolean getIsactive() {
        return  isactive;
    }

    /**
     * 设置 users表的 create_time createTime 字段值
     *
     * @param createTime
     *            将值赋予 users.create_time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  users表的 create_time createTime 字段
     *
     * @return users.create_time
     */
    public Date getCreateTime() {
        return  createTime;
    }


}