package com.zc.enumeration;

/**
 * 用户行为枚举
 * Created by xyzhuzhou on 2016/11/11 0011 14:01:37.
 */
public enum UserBehaviorEnum {


    /**
     * 登录
     * zhuzhzh 2016年11月11日16:11:32
     */
    Login(1),

    /**
     * 登出
     * zhuzhzh 2016年11月11日16:12:01
     */
    LoginOut(2);


    public int getValue() {
        return value;
    }

    private final int value;

    UserBehaviorEnum(int value) {

        this.value = value;
    }
}
