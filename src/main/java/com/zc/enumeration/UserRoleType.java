package com.zc.enumeration;

/**
 * 用户类型
 * Created by xyzhuzhou on 2016/11/11 0011 14:01:37.
 */
public enum UserRoleType {


    ADMIN("admin"),

    USER("user");


    public String getValue() {
        return value;
    }

    private final String value;

    UserRoleType(String value) {

        this.value = value;
    }
}
