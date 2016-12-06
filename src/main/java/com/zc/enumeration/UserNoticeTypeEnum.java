package com.zc.enumeration;

/**
 * 用户通知类别枚举
 * Created by xyzhuzhou on 2016年12月6日12:17:37.
 */
public enum UserNoticeTypeEnum {


    /**
     * 正常全部提示
     * zhuzhzh 2016年12月6日12:39:12
     */
    Normal(0),
    /**
     * 首次登录提示
     * zhuzhzh 2016年11月11日16:11:32
     */
    First(1),
    /**
     * 只提示最后一次提醒
     * zhuzhzh 2016年11月11日16:12:01
     */
    LastNotice(2);


    public int getValue() {
        return value;
    }

    private final int value;

    UserNoticeTypeEnum(int value) {

        this.value = value;
    }
}
