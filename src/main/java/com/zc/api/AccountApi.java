package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.usermodel.LoginModel;
import com.zc.model.usermodel.LoginStatus;
import com.zc.service.UsersService;
import com.zc.utility.ValidateHelper;
import com.zc.utility.response.ApiResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by 张镇强 on 2016/10/14 16:24.
 */
@RestController
@RequestMapping("/api/account/")
public class AccountApi extends BaseApi {
    private final Logger logger = LoggerFactory.getLogger(AccountApi.class);
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ApiResultModel login(@Valid LoginModel loginModel,
                                BindingResult bindResult) {
        ApiResultModel result = new ApiResultModel();
        if (bindResult.hasErrors()) {
            Map<String, String> errData = ValidateHelper
                    .getFieldValidateErrors(bindResult);
            result.setStatusCode(StatusCodeEnum.WRONGPARAM);
            result.setData(errData);
            return result;
        }

        LoginStatus loginStatus = usersService.login(loginModel.getUsername(),
                loginModel.getPassword());
        if (!loginStatus.isLoggedIn()) {
            logger.info("用户{}登录失败", loginModel.getUsername());
            result.setStatusCode(StatusCodeEnum.FAILED);
            return result;
        }

        logger.info("用户{}登录成功", loginModel.getUsername());
        result.setData(loginStatus.getLoginMessage());

        return result;
    }
}
