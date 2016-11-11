package com.zc.service;

import com.zc.bean.UserLog;
import com.zc.enumeration.UserBehaviorEnum;

public interface UserLogService {


    boolean add(UserLog record);

    boolean del(Integer id);

    boolean update(UserLog record);

    UserLog get(Integer id);

    void log(Integer userId, UserBehaviorEnum behaviorEnum, String logInfo);

//    List<UserLog> getAll();
//
//    int delAll();

}