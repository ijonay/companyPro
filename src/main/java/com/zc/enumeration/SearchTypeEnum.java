package com.zc.enumeration;

/**
 * Search Type EnumClass
 * Created by xyzhuzhou on 2016/11/4 0004 14:42:19.
 */
public enum SearchTypeEnum {


    /**
     * 用户兴趣分类
     */
    //UserClass(1),
    UserClass(6),//2016年12月26日14:38:57 更改新的用户分类规则
    /**
     * 事件分类
     */
    EventClass(2),
    /**
     * 性别
     */
    Gender(3),
    /**
     * 地区
     */
    Area(4),
    /**
     * 学历
     */
    Education(5),
    /**
     * 圈层
     */
    Circle(7);


    public int getValue() {
        return value;
    }

    private final int value;

    SearchTypeEnum(int value) {

        this.value = value;
    }
}
