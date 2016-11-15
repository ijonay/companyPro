package com.zc.service;

import com.zc.bean.Users;
import com.zc.dao.UsersMapper;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.enumeration.UserBehaviorEnum;
import com.zc.enumeration.UserRoleType;
import com.zc.model.usermodel.LoginStatus;
import com.zc.model.usermodel.UserSessionModel;
import com.zc.utility.PasswordHelper;
import com.zc.utility.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsersServiceImpl implements UsersService {
    private final static String LOGIN_MSG = "用户名或密码错误";
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserLogService userLogService;

    @Override
    public boolean add(Users record) {
        if (usersMapper.getByUserName(record.getUserName()) != null) {
            throw new ServiceException(StatusCodeEnum.CLIENT_ERROR, "该用户已存在");
        }

        record.setIsactive(true);
        record.setRole(UserRoleType.USER.getValue());

        PasswordHelper.encryptPassword(record);

        return usersMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return usersMapper.del(id) > 0;
    }

    @Override
    public boolean update(Users record) {
        return usersMapper.update(record) > 0;
    }

    @Override
    public Users get(Integer id) {
        return usersMapper.get(id);
    }

    //    @Override
//    public List<Users > getAll() { return usersMapper.getAll();}
//
//    @Override
//    public int delAll() { return usersMapper.delAll(); }

    @Override
    public Users getByUserName(String userName) {
        Users users = usersMapper.getByUserName(userName);
        if (users == null) {
            throw new ServiceException(StatusCodeEnum.SERVER_ERROR, "没有查询到userName=" + userName + "的用户信息");
        }

        return users;
    }

    @Override
    public List<Users> getByRole(String role) {
        return usersMapper.getByRole(role);
    }

    @Override
    public Boolean updateUserState(Integer id, Boolean state) {

        Users user = usersMapper.get(id);
        user.setIsactive(state);
        return usersMapper.update(user) > 0;
    }


    @Override
    public Boolean changePassword(Integer id, String password) {

        Objects.requireNonNull(id);
        Objects.requireNonNull(password);

        Users record = usersMapper.get(id);

        if (record == null) {
            throw new ServiceException(StatusCodeEnum.SERVER_ERROR, "无此用户信息！");
        }
        record.setPassword(password);

        PasswordHelper.encryptPassword(record);

        return usersMapper.update(record) > 0;

    }


    @Override
    public LoginStatus getStatus() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Users users = getByUserName(subject.getPrincipal().toString());
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
                Users users = getByUserName(subject.getPrincipal().toString());

                userLogService.log(users.getId(), UserBehaviorEnum.Login,
                        "");

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

    @Override
    public boolean loginOut() {
        if (getStatus().isLoggedIn()) {
            Subject subject = SecurityUtils.getSubject();

            userLogService.log(getByUserName(subject.getPrincipal().toString()).getId(), UserBehaviorEnum.LoginOut,
                    "");

            subject.logout();
        }
        return true;
    }
}
