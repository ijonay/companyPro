package com.zc.model;

public class WeiboModel {
    private Long id;
    private Long topicid;
    private String weiboContent;
    private Float score;
    private float[] coordinate;
    private String readNum;
    /**
     * 阅读量增长趋势 -1:下降,0:不变,1:上升
     */
    private Short readNumTrendGrowth;
    private String weiboUrl;

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

    public Short getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(Short readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

    public Long getTopicid() {
        return topicid;
    }

    public void setTopicid(Long topicid) {
        this.topicid = topicid;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }
}
