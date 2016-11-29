package com.zc.model;

/**
 * 通用Key,Value模板
 * Created by xyzhuzhou on 2016/11/11 0011 16:24:31.
 */
public class KeyValue<T> {


    public KeyValue(String name, T value) {
        this.name = name;
        this.value = value;
    }


    private String name;
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
