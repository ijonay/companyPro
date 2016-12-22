package com.zc.dao;

import com.zc.bean.UserActionLog;
import com.zc.utility.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserActionLog
 */
public interface UserActionLogMapper {


    int add(UserActionLog record);

    UserActionLog get(Integer id);

    int update(UserActionLog record);

    int del(Integer id);

    List<UserActionLog> getColl(@Param("top") Integer top);

//    List<UserMessage> getByFields(@Param("type") String type
//    , @Param("userId") Integer userId
//    , @Param("userName") String userName
//    , @Param("operaName") String operaName
//    , @Param("info") String info
//    , @Param("pathName") String pathName
//    , @Param("url") String url
//    , @Param("createTime") Date createTime
//     );

    List<UserActionLog> getCollByPage(Page page);

//    int delAll();

}