package com.zc.enumeration;

/**
 * 用户通知类别枚举
 * Created by xyzhuzhou on 2016年12月6日12:17:37.
 */
public enum UserMessageTypeEnum {



    Normal(0),
    /**
     * 用户指导
     * zhuzhzh 2016年11月11日16:11:32
     */
    UserGuide(1),
    /**
     * 系统更新
     * zhuzhzh 2016年11月11日16:12:01
     */
    SystemUpdate(2);


    public int getValue() {
        return value;
    }

    private final int value;

    UserMessageTypeEnum(int value) {

        this.value = value;
    }
}
