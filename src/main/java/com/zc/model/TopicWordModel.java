/** 
 * @title TopicWordModel.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月11日 下午3:23:48 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.model;

public class TopicWordModel {
    private String wordText;
    private float topicWeight;
    private float topicScore;
    private float wordSimilarity;

    /**
     * @return the wordText
     */
    public String getWordText() {
        return wordText;
    }

    /**
     * @param wordText
     *            the wordText to set
     */
    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    /**
     * @return the topicWeight
     */
    public float getTopicWeight() {
        return topicWeight;
    }

    /**
     * @param topicWeight
     *            the topicWeight to set
     */
    public void setTopicWeight(float topicWeight) {
        this.topicWeight = topicWeight;
    }

    /**
     * @return the topicScore
     */
    public float getTopicScore() {
        return topicScore;
    }

    /**
     * @param topicScore
     *            the topicScore to set
     */
    public void setTopicScore(float topicScore) {
        this.topicScore = topicScore;
    }

    /**
     * @return the wordSimilarity
     */
    public float getWordSimilarity() {
        return wordSimilarity;
    }

    /**
     * @param wordSimilarity
     *            the wordSimilarity to set
     */
    public void setWordSimilarity(float wordSimilarity) {
        this.wordSimilarity = wordSimilarity;
    }
}
