package com.zc.api;

import com.zc.bean.Users;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.enumeration.UserRoleType;
import com.zc.service.UsersService;
import com.zc.utility.ParamHelper;
import com.zc.utility.PasswordHelper;
import com.zc.utility.exception.ServiceException;
import com.zc.utility.response.ApiResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by xyzhuzhou on 2016/11/14 0014 17:07:24.
 */
@RestController
@RequestMapping("/api/user/")
public class UserApi extends BaseApi {

    private final Logger logger = LoggerFactory.getLogger(AccountApi.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ApiResultModel getAllUser() {

        ApiResultModel result = new ApiResultModel();

        return result.data(usersService.getByRole(UserRoleType.USER.getValue()));

    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ApiResultModel getUser(@PathVariable("id") Integer id) {

        ApiResultModel result = new ApiResultModel();

        Users user = usersService.get(id);

        user.setPassword("");
        user.setSalt("");

        return result.data(user);

    }

    @RequestMapping(value = "{id}/state", method = RequestMethod.PUT)
    public ApiResultModel updateUserState(@PathVariable("id") Integer id, @RequestParam("state") Boolean state) {

        Objects.requireNonNull(id);

        return new ApiResultModel().data(usersService.updateUserState(id, state));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public ApiResultModel updateUser(@PathVariable("id") Integer id, @RequestBody Users user) {

        Objects.requireNonNull(user);

        Users curUser = usersService.get(user.getId());

        if (curUser == null) {
            throw new ServiceException(StatusCodeEnum.SERVER_ERROR, "无此用户信息！");
        }

        curUser.setCompany(user.getCompany());
        curUser.setNickName(user.getNickName());
        curUser.setUserName(user.getUserName());

        if (ParamHelper.isValidStr(user.getPassword())) {
            curUser.setPassword(user.getPassword());
            PasswordHelper.encryptPassword(curUser);
        }


        return new ApiResultModel().data(usersService.update(curUser));

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ApiResultModel delUser(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        return new ApiResultModel().data(usersService.del(id));
    }

}
