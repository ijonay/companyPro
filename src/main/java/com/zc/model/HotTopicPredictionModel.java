package com.zc.model;

import java.util.Date;

/**
 * Created by zhangcl on 2016/11/9.
 */
public class HotTopicPredictionModel {

    private Integer id;
    private String name;
    private String startDate;
    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
