/**
 * @title PageHelper.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月6日 下午5:35:13
 * Copyright 2016 知藏. All right reserved.
 * 分页帮助类
 */
package com.zc.utility.page;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zc.utility.ParamHelper;

public class Page {

    public Page() {

    }

    public Page(Integer pageNumber) {
        setPageNumber(pageNumber);
    }

    public Page(Integer pageNumber, Integer pageSize) {
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    public Page(boolean disable) {
        setDisable(disable);
    }

    public Page(boolean disable, Integer pageNumber) {
        setDisable(disable);
        setPageNumber(pageNumber);
    }

    public Page(boolean disable, Integer pageNumber, Integer pageSize) {
        setDisable(disable);
        setPageNumber(pageNumber);
        setPageSize(pageSize);
    }

    /**
     * 获取开始项
     *
     * @return
     */
    public int getStartIndex() {

        return (this.getPageNumber() - 1)
                * this.getPageSize();
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        if (ParamHelper.isValidInt(pageSize)) {
            if (this.maxPageSize < pageSize) {
                this.pageSize = this.maxPageSize;
            } else {
                this.pageSize = pageSize;
            }
        }
    }

    /**
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    // /**
    // * @param pageCount
    // * the pageCount to set
    // */
    // public void setPageCount(Integer pageCount) {
    // this.pageCount = pageCount;
    // }

    /**
     * @return the totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        if (ParamHelper.isValidInt(this.totalCount)) {

            this.pageCount = (this.totalCount + this.pageSize - 1)
                    / this.pageSize;

            if (this.pageNumber > this.pageCount) {
                this.pageNumber = this.pageCount;
            }

        }
    }

    /**
     * @return the pageDriver
     */
    @JsonIgnore
    public PageDriver getPageDriver() {
        return pageDriver;
    }

    /**
     * @param pageDriver the pageDriver to set
     */
    public void setPageDriver(PageDriver pageDriver) {
        this.pageDriver = pageDriver;
    }

    /**
     * @return the pageNumber
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(Integer pageNumber) {
        if (ParamHelper.isValidInt(pageNumber)) {
            this.pageNumber = pageNumber;
        }
    }

    /**
     * @return 是否启用分页 true:不启用分页 即使设置分页项当前方法也不会进行分页计算不会进行任何分页操作也不会返回对应信息
     */
    public boolean isDisable() {
        return disable;
    }

    /**
     * @param disable 是否启用分页 true:不启用分页 即使设置分页项当前方法也不会进行分页计算不会进行任何分页操作也不会返回对应信息
     */
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    /**
     * @return the maxPageSize
     */
    public Integer getMaxPageSize() {
        return maxPageSize;
    }

    /**
     * @param maxPageSize the maxPageSize to set
     */
    public void setMaxPageSize(Integer maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    private Integer pageNumber = 1;
    private Integer pageSize = 100;
    private Integer pageCount;
    private Integer totalCount = 0;

    private PageDriver pageDriver;
    private Integer maxPageSize = 200;
    /**
     * 不启用分页
     *
     * @创建人 zhuzhzh @创建时间 2016年6月12日 下午12:18:10
     */
    private boolean disable = false;
    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Page data(Object obj) {
        this.setData(obj);
        return this;
    }
}
