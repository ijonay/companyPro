package com.zc.bean;

import com.zc.model.HotTopicModel;

public class HotTopic {
    private Long id;

    private String title;

    private String coordinate;

    private String keywords;

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

    public HotTopicModel getModel() {
        HotTopicModel model = new HotTopicModel();
        model.setId(id);
        model.setLogoUrl(logoUrl);
        model.setReadNum(readNum);
        model.setReadNumTrendGrowth(readNumTrendGrowth);
        model.setRelationDesc(relationDesc);
        model.setTitle(title);
        model.setTopicType(topicType);
        model.setTopicUrl(topicUrl);
        model.setIntroduction(introduction);

        return model;
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
     * @return the coordinate
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public String getRelationDesc() {
        return relationDesc;
    }

    public void setRelationDesc(String relationDesc) {
        this.relationDesc = relationDesc;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
