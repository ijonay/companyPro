package com.zc.service;

import com.zc.bean.UserNotice;

import java.util.List;

public interface UserNoticeService {


    boolean add(UserNotice record);

    boolean del(Integer id);

    boolean update(UserNotice record);

    UserNotice get(Integer id);


    List<UserNotice> getColl(Integer top);

    List<UserNotice> getByUserAndNotice(Integer userId, Integer noticeId);
//    Page getCollByPage(Page page);

//    int delAll();

}