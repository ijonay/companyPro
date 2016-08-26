package com.zc.model;

import com.zc.enumeration.StatusCodeEnum;

/**
 * Created by 张镇强 on 2016/7/8 11:36.
 */
public class ApiResultModel {
    private Object data;
    private StatusCodeEnum statusCode;
    private String message;

    public ApiResultModel() {
        this.statusCode = StatusCodeEnum.SUCCESS;
        this.setMessage(StatusCodeEnum.SUCCESS.getDesc());
    }

    public ApiResultModel(Object data) {
        this();
        this.setData(data);
    }

    public ApiResultModel(StatusCodeEnum statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiResultModel(Object data, StatusCodeEnum statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode.getValue();
    }

    public void setStatusCode(StatusCodeEnum statusCode) {
        this.statusCode = statusCode;
        this.setMessage(statusCode.getDesc());
    }
}
