package com.zc.dao;

import com.zc.bean.Message;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Message
 */
public interface MessageMapper {


    int add(Message record);

    Message get(Integer id);

    int update(Message record);

    int delByTypeAndTriggerId(Integer id);

    List<Message> getColl(@Param("top") Integer top);

//    List<UserMessage> getByFields(@Param("sender") String sender
//    , @Param("receiver") String receiver
//    , @Param("type") Integer type
//    , @Param("createTime") Date createTime
//    , @Param("expredTime") Date expredTime
//     );

//    List<Message> getCollByPage(Page page);

//    int delAll();


    List<Message> getByType(@Param("type") Integer type, @Param("createTime") Date createTime);

    int delByTypeAndTriggerId(@Param("type") Integer type, @Param("triggerId") Integer triggerId);

}