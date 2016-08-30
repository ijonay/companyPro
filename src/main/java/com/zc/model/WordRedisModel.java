package com.zc.model;

import java.io.Serializable;

/**
 * Created by 张镇强 on 2016/8/29 17:59.
 */
public class WordRedisModel implements Serializable {

    private static final long serialVersionUID = -3766780183428993793L;
    public String name;
    public float score;

    public WordRedisModel(String name, float score) {
        this.setName(name);
        this.setScore(score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "WordRedisModel{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
