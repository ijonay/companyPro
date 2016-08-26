/** 
 * @title TopicModel.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月25日 下午8:42:45 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.model;

public class TopicModel {
    private Long id;
    private String title;
    private Float score;
    private float[] coordinate;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the score
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * @return the coordinate
     */
    public float[] getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(float[] coordinate) {
        this.coordinate = coordinate;
    }
}
