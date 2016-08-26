package com.zc.model;

/**
 * Created by 张镇强 on 2016/7/6 15:17.
 */
public class WordNode {
    private String value;
    private Integer depth;

    public WordNode(String value, Integer depth) {
        this.value = value;
        this.depth = depth;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
}
