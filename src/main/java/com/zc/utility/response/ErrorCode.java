package com.zc.utility.response;

import com.zc.enumeration.StatusCodeEnum;

/**
 * Created by 张镇强 on 2016/9/14 12:24.
 */
public class ErrorCode {
    private StatusCodeEnum code;
    private String message;

    public ErrorCode(StatusCodeEnum statusCode, String message) {
        this.code = statusCode;
        this.message = message;
    }

    public int getCode() {
        return code.getValue();
    }

    public void setCode(StatusCodeEnum code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
