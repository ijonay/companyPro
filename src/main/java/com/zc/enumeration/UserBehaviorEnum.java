package com.zc.enumeration;

/**
 * Created by xyzhuzhou on 2016/11/11 0011 14:01:37.
 */
public enum UserBehaviorEnum {


    Login(1),

    LoginOut(2);


    public int getValue() {
        return value;
    }

    private final int value;

    UserBehaviorEnum(int value) {

        this.value = value;
    }
}
