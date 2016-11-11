package com.zc.model;

/**
 * 通用Key,Value模板
 * Created by xyzhuzhou on 2016/11/11 0011 16:24:31.
 */
public class KeyValue {

    private String name;
    private Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
