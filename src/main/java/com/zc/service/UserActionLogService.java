package com.zc.service;

import com.zc.bean.UserActionLog;
import com.zc.utility.page.Page;

import java.util.List;

public interface UserActionLogService {


    boolean add(UserActionLog record);

    boolean del(Integer id);

    boolean update(UserActionLog record);

    UserActionLog get(Integer id);


    List<UserActionLog> getColl(Integer top);

    Page getCollByPage(Page page);

//    int delAll();

}