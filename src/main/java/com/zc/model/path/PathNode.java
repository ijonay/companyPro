package com.zc.model.path;

/**
 * Created by 张镇强 on 2016/9/7 14:58.
 */
public class PathNode {
    private String name;
    private float similarity; // 路径中的点和之前一个词的相似度

    public PathNode(String name, float similarity) {
        this.setName(name);
        this.setSimilarity(similarity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSimilarity() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity = similarity;
    }

}
