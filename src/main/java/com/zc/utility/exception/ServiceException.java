/**
 * @title ServiceException.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年6月2日 下午12:41:54
 * Copyright 2016 知藏. All right reserved.
 * service层自定义错误
 */
package com.zc.utility.exception;

import com.zc.enumeration.StatusCodeEnum;

public class ServiceException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * @param code
     * @创建人 zhuzhzh @创建时间 2016年6月2日 下午2:19:36
     */
    public ServiceException(StatusCodeEnum code) {
        super(code, "");
    }

    /**
     * @param code
     * @param msg
     * @创建人 zhuzhzh @创建时间 2016年6月2日 下午2:19:36
     */
    public ServiceException(StatusCodeEnum code, String msg) {
        super(code, msg);
    }

}
