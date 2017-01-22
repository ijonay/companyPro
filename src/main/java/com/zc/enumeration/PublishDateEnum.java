package com.zc.enumeration;/**
 * Created by xyzhuzhou on 2017/1/18 0018 11:09:29.
 */

/**
 * 发布推送日期
 * Created by xyzhuzhou on 2017/1/18 0018 11:09:29.
 */
public enum PublishDateEnum {


    /**
     * 今日
     */
    TODAY(0),
    /**
     * 近七天
     */
    INSEVENDAYS(1),
    /**
     * 近三十天
     */
    INTHIRTYDAYS(2);

    private final int value;

    PublishDateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
