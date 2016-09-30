package com.zc.tempbean;

public class WeiboInfo {
    private Integer id;
    private Integer topicid;
    private String weiboUrl;
    private String readNum;
    private String weiboContent;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public String getWeibourl() {
        return weiboUrl;
    }

    public void setWeibourl(String weibourl) {
        this.weiboUrl = weibourl;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

   
}
