package com.zc.bean;

import com.zc.model.WeiboModel;

public class Weibo {
    private Integer id;
    private Integer topicid;
    private String weiboContent;
    private String keyWords;
    private String coordinate;
    private String readNum;
    private Short readNumTrendGrowth;
    private String weiboUrl;

   public WeiboModel getModel(){
       WeiboModel model=new WeiboModel();
       model.setId(id.longValue());
       model.setReadNum(readNum);
       model.setReadNumTrendGrowth(getReadNumTrendGrowth());
       model.setTopicid(topicid.longValue());
       model.setWeiboContent(weiboContent);
       model.setWeiboUrl(weiboUrl);
       return model;
   }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public Short getReadNumTrendGrowth() {
        return readNumTrendGrowth;
    }

    public void setReadNumTrendGrowth(Short readNumTrendGrowth) {
        this.readNumTrendGrowth = readNumTrendGrowth;
    }

}
