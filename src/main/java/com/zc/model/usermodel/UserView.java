/**
 * @title UserModel.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月1日 下午5:40:50
 * Copyright 2016 知藏. All right reserved.
 * 用户ViewModel
 */
package com.zc.model.usermodel;

import com.zc.bean.Users;

public class UserView {

    private Integer id;
    private String userName;
    private String nickName;
    /**
     * company ,所属表字段为users.company
     */
    private String company;


    /**
     * 是否是否初始化账号
     */
    private boolean firstUserAccount = false;

    /**
     * 是否含有系统更新
     */
    private boolean hasProjUpdate = false;


    /**
     * @创建人 zhuzhzh @创建时间 2016年6月2日 下午3:31:24
     */
    public UserView(Users model) {
        if (model == null)
            return;

        this.setId(model.getId());
        this.setUserName(model.getUserName());
        this.setNickName(model.getNickName());
        this.setCompany(model.getCompany());


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 设置 表的  company 字段值
     *
     * @param company 将值赋予 users.company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取  表的  company 字段
     *
     * @return users.company
     */
    public String getCompany() {
        return company;
    }

    public boolean isFirstUserAccount() {
        return firstUserAccount;
    }

    public void setFirstUserAccount(boolean firstUserAccount) {
        this.firstUserAccount = firstUserAccount;
    }

    public boolean isHasProjUpdate() {
        return hasProjUpdate;
    }

    public void setHasProjUpdate(boolean hasProjUpdate) {
        this.hasProjUpdate = hasProjUpdate;
    }
}
