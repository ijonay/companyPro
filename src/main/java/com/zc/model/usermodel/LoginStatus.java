/**
 * @Title LoginStatus.java
 * @author 张镇强/zhangzq@heptax.com
 * @date：2016年4月27日 Copyright 2016 知藏. All right reserved.
 * [描述]
 */
package com.zc.model.usermodel;

/**
 * @author 张镇强 / zhangzq@heptax.com
 */
public class LoginStatus {

    /**
     * 是否已经登录
     */
    private final boolean loggedIn;
    private final UserSessionModel userSessionModel;
    private final String loginMessage;

    /**
     * @param loggedIn
     * @param userSessionModel
     */
    public LoginStatus(boolean loggedIn, UserSessionModel userSessionModel, String loginMessage) {
        super();
        this.loggedIn = loggedIn;
        this.userSessionModel = userSessionModel;
        this.loginMessage = loginMessage;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    public String getLoginMessage() {
        return loginMessage;
    }

    public UserSessionModel getUserSessionModel() {
        return userSessionModel;
    }
}
