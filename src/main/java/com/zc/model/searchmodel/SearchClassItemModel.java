package com.zc.model.searchmodel;

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
}
