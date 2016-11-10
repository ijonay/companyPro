/**
 * @title PageHelper.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月6日 下午5:35:13
 * Copyright 2016 知藏. All right reserved.
 * 分页帮助类
 */
package com.zc.utility;


import static com.zc.utility.ParamHelper.isValidInt;

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
            this.pageSize = pageSize;
        }
    }

    /**
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }


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
        if (isValidInt(this.totalCount)) {
            this.pageCount = (this.totalCount + this.pageSize - 1)
                    / this.pageSize;
        }
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
        if (isValidInt(pageNumber)) {
            this.pageNumber = pageNumber;
        }
    }


    private Integer pageNumber = 1;
    private Integer pageSize = 10;
    private Integer pageCount;
    private Integer totalCount;
    private Object data;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
