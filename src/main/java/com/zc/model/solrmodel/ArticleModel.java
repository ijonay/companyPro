package com.zc.model.solrmodel;

import java.util.Date;
import java.util.List;

/**
 * ArticleModel
 */
public class ArticleModel {

    private String id;
    private String title;
    private String article_url;
    private List<String> title_participle;
    private String articleTags;
    private String titleStruct;
    private String account_id;
    private String account_name;
    private Integer read_num;
    private String articleType;
    private String structure_type;
    private Date publish_time;
    private Float relative_score;
    private String keywords;
    //    private String content;
    //    private String raw_content;
    private Float score;
    private Integer topicId;
    private String topicTitle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getStructure_type() {
        return structure_type;
    }

    public void setStructure_type(String structure_type) {
        this.structure_type = structure_type;
    }


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

//    public String getRaw_content() {
//        return raw_content;
//    }
//
//    public void setRaw_content(String raw_content) {
//        this.raw_content = raw_content;
//    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Float getRelative_score() {
        return relative_score;
    }

    public void setRelative_score(Float relative_score) {
        this.relative_score = relative_score;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getTitleStruct() {
        return titleStruct;
    }

    public void setTitleStruct(String titleStruct) {
        this.titleStruct = titleStruct;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Integer getRead_num() {
        return read_num;
    }

    public void setRead_num(Integer read_num) {
        this.read_num = read_num;
    }

    public List<String> getTitle_participle() {
        return title_participle;
    }

    public void setTitle_participle(List<String> title_participle) {
        this.title_participle = title_participle;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }
}