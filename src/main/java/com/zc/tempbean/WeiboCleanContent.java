package com.zc.tempbean;

public class WeiboCleanContent {
    private Integer weibo_id;
    private String weibo_content_cleansed;
    private String comment_content_cleansed;

    public Integer getWeibo_id() {
        return weibo_id;
    }

    public void setWeibo_id(Integer weibo_id) {
        this.weibo_id = weibo_id;
    }

    public String getWeibo_content_cleansed() {
        return weibo_content_cleansed;
    }

    public void setWeibo_content_cleansed(String weibo_content_cleansed) {
        this.weibo_content_cleansed = weibo_content_cleansed;
    }

    public String getComment_content_cleansed() {
        return comment_content_cleansed;
    }

    public void setComment_content_cleansed(String comment_content_cleansed) {
        this.comment_content_cleansed = comment_content_cleansed;
    }
}
