package com.zc.api;

import com.zc.enumeration.UserRoleType;
import com.zc.service.UsersService;
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

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ApiResultModel delUser(@PathVariable("id") Integer id, @RequestParam("state") Boolean state) {

        Objects.requireNonNull(id);

        return new ApiResultModel().data(usersService.updateUserState(id, state));
    }


}
