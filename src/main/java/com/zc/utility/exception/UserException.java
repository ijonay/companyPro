/**
 * @title UserException.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月2日 下午4:00:07
 * Copyright 2016 知藏. All right reserved.
 * user自定义异常
 */
package com.zc.utility.exception;

import com.zc.enumeration.StatusCodeEnum;

public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(StatusCodeEnum code, String msg) {
        super(code, msg);
    }

}
