package com.zc.service;

import com.zc.bean.Users;
import com.zc.model.usermodel.LoginStatus;

public interface UsersService {

    boolean add(Users record);

    boolean del(Integer id);

    boolean update(Users record);

    Users get(Integer id);

    Users getByUserName(String userName);

    /**
     * 获取登录状态
     *
     * @return
     * @throws Exception
     * @author: 张镇强/zhangzq@heptax.com
     */
    LoginStatus getStatus();

    LoginStatus login(String username, String password);
}