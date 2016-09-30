/** 
 * @title Topic.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月17日 下午12:14:51 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.bean;

import java.util.List;

import com.zc.model.TopicModel;

public class Topic {

    private Long id;

    private String title;

    private String coordinate;

    private String keywords;

    private String logoUrl;
    
    private String readNum;
    
    
    /**
     * 阅读量增长趋势 -1:下降,0:不变,1:上升
     */
    private Short readNumTrendGrowth;
    
    private String relationDesc;
    
    private String topicUrl;
    
    private String topicType;
    
    private List<WordDataRelations> words;
    
    public TopicModel getModel(){
        TopicModel model=new TopicModel();
        model.setId(id);
        model.setTitle(title);
        model.setLogoUrl(logoUrl);
        model.setReadNum(readNum);
        model.setReadNumTrendGrowth(getReadNumTrendGrowth());
        model.setRelationDesc(relationDesc);
        model.setTitle(title);
        model.setTopicType(topicType);
        model.setTopicUrl(topicUrl);
        return model;
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
     * @return the coordinate
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate
     *            the coordinate to set
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
     * @param id
     *            the id to set
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
     * @param keywords
     *            the keywords to set
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

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
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
    public Short getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }
    public void setReadNumTrendGrowth(Short readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }
    public List<WordDataRelations> getWords() {
        return words;
    }
    public void setWords(List<WordDataRelations> words) {
        this.words = words;
    }
    
}
