package com.zc.utility.response;

import com.zc.enumeration.StatusCodeEnum;

/**
 * Created by 张镇强 on 2016/7/8 11:36.
 */
public class ApiResultModel {
    private Object data;
    private ErrorCode error;

    public ApiResultModel() {
        this.error = new ErrorCode(StatusCodeEnum.SUCCESS, StatusCodeEnum.SUCCESS.getDesc());
    }


    public ApiResultModel(StatusCodeEnum statusCode) {
        this.error = new ErrorCode(statusCode, statusCode.getDesc());
    }

    public ApiResultModel(StatusCodeEnum statusCode, String message) {
        this.error = new ErrorCode(statusCode, message);
    }

    public ApiResultModel(Object data, StatusCodeEnum statusCode) {
        this(statusCode, statusCode.getDesc());
        this.data = data;
    }

    public ApiResultModel(Object data, StatusCodeEnum statusCode, String message) {
        this(statusCode, message);
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.error.setMessage(message);
    }

    public ErrorCode getError() {
        return error;
    }

//    public String getMessage() {
//        return this.error.getMessage();
//    }

//    public int getStatusCode() {
//        return this.error.getStatusCode().getValue();
//    }

    public ApiResultModel setStatusCode(StatusCodeEnum statusCode) {
        this.error.setCode(statusCode);
        this.setMessage(statusCode.getDesc());

        return this;
    }

    public ApiResultModel data(Object data) {
        this.setData(data);
        return this;
    }

    public ApiResultModel code(StatusCodeEnum statusCodeEnum) {
        this.setStatusCode(statusCodeEnum);
        return this;
    }

    public ApiResultModel message(String message) {
        this.setMessage(message);
        return this;
    }
}
