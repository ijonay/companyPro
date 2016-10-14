package com.zc.dao;

import com.zc.bean.Users;

/**
 *
 */
public interface UsersMapper {


    int add(Users record);

    Users get(Integer id);

    int update(Users record);

    int del(Integer id);

    Users getByUserName(String userName);
}