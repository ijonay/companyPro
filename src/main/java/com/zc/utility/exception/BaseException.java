/**
 * @title BaseException.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月2日 下午3:54:34
 * Copyright 2016 知藏. All right reserved.
 * 自定义异常基类
 */
package com.zc.utility.exception;

import com.zc.enumeration.StatusCodeEnum;

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private StatusCodeEnum code;
    private String msg;

    /**
     * @param code
     * @param msg
     * @创建人 zhuzhzh @创建时间 2016年6月2日 下午3:56:29
     */
    public BaseException(StatusCodeEnum code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public StatusCodeEnum getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(StatusCodeEnum code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
