/**
 * @Title ValidatorHelper.java
 * @author 张镇强/zhangzq@heptax.com
 * @date：2016年4月14日 Copyright 2016 知藏. All right reserved.
 * [描述]
 */
package com.zc.utility;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.response.ApiResultModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 张镇强 / zhangzq@heptax.com
 */
public interface ValidateHelper {
    static ApiResultModel handleFieldValidateErrors(BindingResult bindingResult) {
        ApiResultModel result = new ApiResultModel();
        result.setData(ValidateHelper.getFieldValidateErrors(bindingResult));
        result.setStatusCode(StatusCodeEnum.WRONGPARAM);

        return result;
    }

    static Map<String, String> getFieldValidateErrors(BindingResult bindResult) {
        List<FieldError> fieldErrors = bindResult.getFieldErrors();

        Map<String, String> errorMap = new HashMap<>();

        for (FieldError err : fieldErrors) {
            errorMap.put(err.getField(), err.getDefaultMessage());
        }

        return errorMap;
    }

}
