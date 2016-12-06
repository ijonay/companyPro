package com.zc.dao;

import com.zc.bean.UserNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User_Notice
 */
public interface UserNoticeMapper {


    int add(UserNotice record);

    UserNotice get(Integer id);

    int update(UserNotice record);

    int del(Integer id);

    List<UserNotice> getColl(@Param("top") Integer top);

    List<UserNotice> getByUserAndNotice(@Param("userId") Integer userId, @Param("noticeId") Integer noticeId);

//    List<UserNotice> getCollByPage(Page page);

//    int delAll();

}