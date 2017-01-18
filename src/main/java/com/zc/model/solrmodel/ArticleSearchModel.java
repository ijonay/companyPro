package com.zc.model.solrmodel;/**
 * Created by xyzhuzhou on 2017/1/18 0018 10:28:48.
 */

import com.zc.enumeration.PublishDateEnum;
import com.zc.utility.page.Page;

import java.util.List;

/**
 * Created by xyzhuzhou on 2017/1/18 0018 10:28:48.
 */
public class ArticleSearchModel extends Page {

    /**
     * 领域
     */
    private List<String> tags;
    /**
     * 文章结构
     */
    private List<String> structTypes;
    /**
     * 发布日期
     */
    private PublishDateEnum publishDate;
    /**
     * 关键词
     */
    private String keywords;


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getStructTypes() {
        return structTypes;
    }

    public void setStructTypes(List<String> structTypes) {
        this.structTypes = structTypes;
    }

    public PublishDateEnum getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(PublishDateEnum publishDate) {
        this.publishDate = publishDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
