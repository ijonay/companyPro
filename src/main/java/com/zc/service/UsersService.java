package com.zc.service;

import com.zc.bean.Users;
import com.zc.model.usermodel.LoginStatus;

import java.util.List;

public interface UsersService {

    boolean add(Users record);

    boolean del(Integer id);

    boolean update(Users record);

    Users get(Integer id);

//    List<Users> getAll();
//
//    int delAll();

    Users getByUserName(String userName);

    List<Users> getByRole(String role);

    Boolean updateUserState(Integer id, Boolean state);


    Boolean changePassword(Integer id, String password);

    /**
     * 获取登录状态
     *
     * @return
     * @throws Exception
     * @author: 张镇强/zhangzq@heptax.com
     */
    LoginStatus getStatus();

    LoginStatus login(String username, String password);

    /**
     * 退出登录
     * zhuzhzh 2016年11月11日12:19:42
     *
     * @return
     */
    boolean loginOut();
}