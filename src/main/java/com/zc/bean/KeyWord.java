/** 
 * @title KeyWord.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月25日 下午5:28:59 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.bean;

public class KeyWord {
    private String word;
    private float score;
    private float weight;
    private float[]coordinate;

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word
     *            the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight
     *            the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(float[] coordinate) {
        this.coordinate = coordinate;
    }
}
