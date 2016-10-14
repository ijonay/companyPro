/**
 * @title UserModel.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月1日 下午5:40:50
 * Copyright 2016 知藏. All right reserved.
 * 用户ViewModel
 */
package com.zc.model.usermodel;

import com.zc.bean.Users;

public class UserModel {

    private Integer id;
    private String userName;
    private String passWord;
    private String salt;
    private String nickName;
    private String avatar;

    /**
     * @创建人 zhuzhzh @创建时间 2016年6月2日 下午3:31:24
     */
    public UserModel(Users model) {
        if (model == null)
            return;

        this.setId(model.getId());
        this.setUserName(model.getUserName());
        this.setPassWord(model.getPassword());
        this.setSalt(model.getSalt());
        this.setNickName(model.getNickName());
        this.setAvatar(model.getAvatar());
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
