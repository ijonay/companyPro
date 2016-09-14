package com.zc.utility.response;

import com.zc.enumeration.StatusCodeEnum;

/**
 * Created by 张镇强 on 2016/9/14 12:24.
 */
public class ErrorCode {
    private StatusCodeEnum statusCode;
    private String message;

    public ErrorCode(StatusCodeEnum statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode.getValue();
    }

    public void setStatusCode(StatusCodeEnum statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
