package com.zc.model;

public class HotTopicModel {
    private Long id;
    private String title;
    private Float score;
    private String logoUrl;
    private Long readNum;
    /**
     * 阅读量增长趋势 -1:下降,0:不变,1:上升
     */
    private Short readNumTrendGrowth;
    private String relationDesc;
    private String topicUrl;
    private String topicType;
    private String introduction;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
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
     * @param title the title to set
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
     * @param score the score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Short getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(Short readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
