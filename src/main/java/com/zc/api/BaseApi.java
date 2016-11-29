/**
 * @title BaseApi.java
 * @author zhuzhzh/zhuzz@heptax.com
 * @date：2016年3月4日 下午4:26:36
 * Copyright 2016 知藏. All right reserved.
 * Api基类
 */
package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.StringHelper;
import com.zc.utility.exception.BaseException;
import com.zc.utility.response.ApiResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

public class BaseApi {
    protected final Logger apiLogger = LoggerFactory.getLogger(BaseApi.class);

    @ExceptionHandler
    @ResponseBody
    public ApiResultModel exp(HttpServletRequest request, Exception ex) {


        request.setAttribute("ex", ex);
        ApiResultModel result = new ApiResultModel();

        ex.printStackTrace();

        /* 处理自义定异常 */
        if (ex instanceof BaseException) {
            BaseException exception = (BaseException) ex;
            result.setStatusCode(exception.getCode());
            String errMsg = exception.getMsg();
            if (StringHelper.isEmpty(errMsg)) {
                errMsg = exception.getMessage();
            }

            result.setMessage(errMsg);

            apiLogger.error(ex.toString() + " code:{} message:{} stacktrace:{}",
                    new Object[]{exception.getCode(), errMsg,
                            ex.getStackTrace()});

        } else if (ex instanceof MethodArgumentTypeMismatchException) {

            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;

            apiLogger.error("ServiceException code:{} message:{} stacktrace:{}",
                    new Object[]{exception.getErrorCode(),
                            exception.getMessage(), ex.getStackTrace()});

            return result.code(StatusCodeEnum.WRONGPARAM).message(exception.getMessage());

        } else {
            apiLogger.error("Exception message:{} stacktrace:{}",
                    ex.getMessage(), ex.getStackTrace());
            return result.code(StatusCodeEnum.FAILED).message(ex.getMessage());
        }

        return result;
    }
}