/**
 * @Title UserSessionModel.java
 * @author 张镇强/zhangzq@heptax.com
 * @date：2016年4月25日 Copyright 2016 知藏. All right reserved.
 * session中存储的用户对象
 */
package com.zc.model.usermodel;

import com.zc.bean.Users;
import com.zc.utility.StringHelper;

/**
 * @author 张镇强 / zhangzq@heptax.com
 */
public class UserSessionModel {
    private int userId;
    private String userName;
    private String nickName;
    private String avatar;

    public UserSessionModel(Users entity) {
        super();
        this.userId = entity.getId();
        this.userName = entity.getUserName();
        this.nickName = StringHelper.isEmpty(entity.getNickName()) ?
                entity.getUserName() : entity.getNickName();
        this.avatar = entity.getAvatar();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
