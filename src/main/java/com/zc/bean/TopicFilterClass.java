package com.zc.bean;

/**
 *
 */
public class TopicFilterClass extends TopicFilter {

    /**
     * name ,所属表字段为TopicClass.name
     */
    private String name;

    private Integer topicScaleTotal;

    private Double topicPercentageTotal;

    /**
     * scale ,所属表字段为TopicClass.scale
     */
    private Integer scaleTotal;

    /**
     * percentage ,所属表字段为TopicClass.percentage
     */
    private Double percentageTotal;

    public Double getPercentageTotal() {
        return percentageTotal;
    }

    public void setPercentageTotal(Double percentageTotal) {
        this.percentageTotal = percentageTotal;
    }

    public Integer getScaleTotal() {
        return scaleTotal;
    }

    public void setScaleTotal(Integer scaleTotal) {
        this.scaleTotal = scaleTotal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopicScaleTotal() {
        return topicScaleTotal;
    }

    public void setTopicScaleTotal(Integer topicScaleTotal) {
        this.topicScaleTotal = topicScaleTotal;
    }

    public Double getTopicPercentageTotal() {
        return topicPercentageTotal;
    }

    public void setTopicPercentageTotal(Double topicPercentageTotal) {
        this.topicPercentageTotal = topicPercentageTotal;
    }
}