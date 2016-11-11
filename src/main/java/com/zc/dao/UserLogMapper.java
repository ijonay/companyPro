package com.zc.dao;

import com.zc.bean.UserLog;

/**
 * 
 */
public interface UserLogMapper {


    int add(UserLog record);

    UserLog get(Integer id);

    int update(UserLog record);

    int del(Integer id);

//    List<UserLog> getAll();
//
//    int delAll();

}