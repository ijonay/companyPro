/**
 * @title ParameterException.java
 * @author xyzhuzhou/zhuzz@heptax.com
 * @date：2016年7月20日 下午2:46:41
 * @Tag 参数异常错误类
 * @Copyright 2016 知藏. All right reserved.
 */
package com.zc.utility.exception;

import com.zc.enumeration.StatusCodeEnum;

@SuppressWarnings("serial")
public class ParameterException extends BaseException {

    public ParameterException() {
        super(StatusCodeEnum.WRONGPARAM, "");
    }

    public ParameterException(String msg) {
        super(StatusCodeEnum.WRONGPARAM, msg);
    }

}
