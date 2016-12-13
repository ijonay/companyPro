package com.zc.dao;

import com.zc.bean.UserMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * User_Message
 */
public interface UserMessageMapper {


    int add(UserMessage record);

    UserMessage get(Integer id);

    int update(UserMessage record);

    int del(Integer id);

    List<UserMessage> getColl(@Param("top") Integer top);

    List<UserMessage> getByFields(@Param("messageId") Integer messageId
            , @Param("userId") Integer userId
            , @Param("createTime") Date createTime
            , @Param("readTime") Date readTime
    );

    UserMessage getByUserAndMsg(@Param("messageId") Integer messageId, @Param("userId") Integer userId);


//    List<UserMessage> getCollByPage(Page page);

//    int delAll();

}