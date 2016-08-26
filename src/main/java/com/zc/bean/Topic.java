/** 
 * @title Topic.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年8月17日 下午12:14:51 
 * Copyright 2016 知藏. All right reserved.
 * 类说明
 */
package com.zc.bean;

public class Topic {

    private Long id;

    private String title;

    private String coordinate;

    private String keywords;

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
}
