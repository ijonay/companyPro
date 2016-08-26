package com.zc.model;

/**
 * 词模型对象
 * Created by 张镇强 on 2016/7/8 15:51.
 */
public class WordModel {
    private String code;
    private String name;
    private String modelPath;


    private String filterListPath;

    public WordModel(String code, String name, String modelPath, String filterListPath) {
        this.code = code;
        this.name = name;
        this.modelPath = modelPath;
        this.filterListPath = filterListPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getFilterListPath() {
        return filterListPath;
    }

    public void setFilterListPath(String filterListPath) {
        this.filterListPath = filterListPath;
    }
}
