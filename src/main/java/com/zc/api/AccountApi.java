package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.usermodel.LoginModel;
import com.zc.model.usermodel.LoginStatus;
import com.zc.model.usermodel.RegisterModel;
import com.zc.service.UsersService;
import com.zc.utility.ValidateHelper;
import com.zc.utility.response.ApiResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

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
            result.setStatusCode(StatusCodeEnum.FAILED)
                    .setMessage(loginStatus.getLoginMessage());

            return result;
        }

        logger.info("用户{}登录成功", loginModel.getUsername());
        result.setData(loginStatus.getLoginMessage());

        return result;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ApiResultModel register(@RequestBody @Valid RegisterModel model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidateHelper.handleFieldValidateErrors(bindingResult);
        }

        ApiResultModel resultModel = new ApiResultModel();
        // TOODO: 这个地方应该用注解的方式去验证
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            resultModel.setStatusCode(StatusCodeEnum.WRONGPARAM).setMessage("密码不相符");
            return resultModel;
        }

        if (!usersService.add(model.toEntity())) {
            resultModel.setStatusCode(StatusCodeEnum.FAILED);
        }

        return resultModel;
    }

    @RequestMapping(value = "loginout", method = RequestMethod.GET)
    public ApiResultModel loginOut() {

        return new ApiResultModel().data(usersService.loginOut());


    }

    @RequestMapping(value = "{id}/changep", method = RequestMethod.POST)
    public ApiResultModel changePass(@PathVariable("id") Integer userId,
                                     @RequestParam(value = "password") String password) {


        Objects.requireNonNull(password);
        Objects.requireNonNull(userId);

        ApiResultModel result = new ApiResultModel();

        return result.data(usersService.changePassword(userId, password));

    }
}
