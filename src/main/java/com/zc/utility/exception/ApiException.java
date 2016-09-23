/**
 * @title ApiException.java
 * @author huyulinhome/huyl@heptax.com
 * @date：2016年3月11日 上午11:33:22
 * Copyright 2016 知藏. All right reserved.
 * Api层自定义异常
 */
package com.zc.utility.exception;


import com.zc.enumeration.StatusCodeEnum;

public class ApiException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ApiException(StatusCodeEnum code) {
        super(code, "");
    }

    public ApiException(StatusCodeEnum code, String msg) {
        super(code, msg);
    }

}
