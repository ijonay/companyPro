package com.zc.model.path;

/**
 * Created by 张镇强 on 2016/9/7 14:58.
 */
public class PathNode {
    private String name;
    private String prevName;
    private float similarity;

    public PathNode(String name, String prevName, float similarity) {
        this.name = name;
        this.prevName = prevName;
        this.similarity = similarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrevName() {
        return prevName;
    }

    public void setPrevName(String prevName) {
        this.prevName = prevName;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }
}
