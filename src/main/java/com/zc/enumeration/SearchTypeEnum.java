package com.zc.enumeration;

/**
 * Search Type EnumClass
 * Created by xyzhuzhou on 2016/11/4 0004 14:42:19.
 */
public enum SearchTypeEnum {


    UserClass(1),
    EventClass(2),
    Gender(3),
    Area(4),
    Education(5);


    public int getValue() {
        return value;
    }

    private final int value;

    SearchTypeEnum(int value) {

        this.value = value;
    }
}
