/**
 * @Title ShiroLoginService.java
 * @author 张镇强/zhangzq@heptax.com
 * @date：2016年4月27日 Copyright 2016 知藏. All right reserved.
 * [描述]
 */
package com.zc.shiro;

import com.zc.bean.Users;
import com.zc.model.usermodel.LoginStatus;
import com.zc.model.usermodel.UserSessionModel;
import com.zc.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * shiro登录接口实现
 *
 * @author 张镇强 / zhangzq@heptax.com
 */
@Service
public class ShiroLoginService implements LoginService {
    @Autowired
    UsersService userService;

    private final static String LOGIN_MSG = "用户名或密码错误";

    @Override
    public LoginStatus getStatus() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Users users = userService.getByUserName(subject.getPrincipal().toString());
            return new LoginStatus(true, new UserSessionModel(users), "已登录");
        } else {
            return new LoginStatus(false, null, "未登录");
        }
    }

    @Override
    public LoginStatus login(String username, String password) {
        if (getStatus().isLoggedIn()) {
            return getStatus();
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                subject.login(token);
                Users users = userService.getByUserName(subject.getPrincipal().toString());
                return new LoginStatus(true, new UserSessionModel(users), "登录成功");
            } catch (UnknownAccountException e) {
                return new LoginStatus(false, null, LOGIN_MSG);
            } catch (IncorrectCredentialsException e) {
                return new LoginStatus(false, null, LOGIN_MSG);
            } catch (AuthenticationException e) {
                return new LoginStatus(false, null, LOGIN_MSG);
            }
        }
    }
}
