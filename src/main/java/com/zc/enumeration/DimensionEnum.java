package com.zc.enumeration;

/**
 * 话题搜索维度
 * Created by xyzhuzhou on 2016/11/11 0011 16:09:42.
 */
public enum DimensionEnum {

    /**
     * 兴趣爱好
     */
    Interest(1),
    /**
     * 事件分类
     */
    Event(2),
    /**
     * 用户性别
     */
    Gender(3),
    /**
     * 用户区域
     */
    Area(4),
    /**
     * 学历
     */
    Eeducation(5),
    /**
     * 年龄
     */
    Age(6);


    public int getValue() {
        return value;
    }

    private final int value;

    DimensionEnum(int value) {

        this.value = value;
    }
}
