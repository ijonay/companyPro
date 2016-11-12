package com.zc.model;

/**
 * Created by zhangcl on 2016/11/12.
 */
public class UserRecommendedTopicModel {

    private Integer id;
    private String keyword;
    private Integer topicId;
    private String title;
    private String createDate;
    /*private Float score;
    private float[] coordinate;
    private String logoUrl;
    private String readNum;
    private Integer readNumTrendGrowth;
    private String relationDesc;
    private String topicUrl;
    private String topicType;
    private String introduction;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
