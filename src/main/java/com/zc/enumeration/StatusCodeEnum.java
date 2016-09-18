/**
 * @title StatusCode.java
 * @author 张镇强/zhangzq@zhicang.market
 * @date：2016年1月22日 下午12:50:31
 * Copyright 2016 知藏. All right reserved.
 * 自定义状态码枚举
 */
package com.zc.enumeration;

public enum StatusCodeEnum {

    FAILED(-1, "出错"),
    SUCCESS(0, "成功"),
    NOCONTENT(2004, "没有数据"),
    WRONGPARAM(4001, "参数错误"),
    WARN(4002, "警告"),
    CLIENT_ERROR(4003, "客户端错误"),
    UNAUTHENTICATED(4005, "没有登录"),
    UNAUTHORIZTION(4006, "没有权限"),
    SERVER_ERROR(5000, "服务器端错误");

    private final int value;
    private final String desc;

    StatusCodeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public String getDesc() {
        return this.desc;
    }
}