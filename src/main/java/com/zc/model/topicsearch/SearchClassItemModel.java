package com.zc.model.topicsearch;

import com.zc.bean.TopicClass;

import java.util.List;
import java.util.Objects;

/**
 * Created by xyzhuzhou on 2016/11/4 0004 12:31:52.
 */
public class SearchClassItemModel {


    public SearchClassItemModel(TopicClass model) {

        Objects.requireNonNull(model);

        setId(model.getId());
        setName(model.getName());
        setDescription(model.getDescription());
        setRule(model.getRule());
    }

    /**
     * ID
     */
    private Integer id;

    /**
     * 类别名称
     */
    private String name;
    /**
     * description description ,所属表字段为TopicClass.description
     */
    private String description;

    /**
     * rule rule ,所属表字段为TopicClass.rule
     */
    private String rule;

    /**
     * 子集
     */
    private List<SearchClassItemModel> childs;


    public List<SearchClassItemModel> getChilds() {
        return childs;
    }

    public void setChilds(List<SearchClassItemModel> childs) {
        this.childs = childs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
