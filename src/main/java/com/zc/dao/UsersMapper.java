package com.zc.dao;

import com.zc.bean.Users;

import java.util.List;

/**
 *
 */
public interface UsersMapper {


    int add(Users record);

    Users get(Integer id);

    int update(Users record);

    int del(Integer id);


//    List<Users> getAll();
//
//    int delAll();

    List<Users> getByRole(String role);

    Users getByUserName(String userName);
}