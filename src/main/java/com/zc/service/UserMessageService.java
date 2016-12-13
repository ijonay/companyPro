package com.zc.service;

import com.zc.bean.UserMessage;

import java.util.List;

public interface UserMessageService {


    boolean add(UserMessage record);

    boolean del(Integer id);

    boolean update(UserMessage record);

    UserMessage get(Integer id);


    List<UserMessage> getColl(Integer top);

    /**
     * 获取用户是否初始状态
     *
     * @return
     */
    boolean getUserInitState(Integer userId);

    /**
     * 更改用户初始状态  并返回当前用户是否处于初始状态
     *
     * @param userId
     * @return
     */
    boolean changeUserInitState(Integer userId);


    /**
     * 获取项目更新是否提示
     *
     * @param userId
     * @return
     */
    boolean getProjUpdateMsg(Integer userId);

    /**
     * 更新项目更新提示信息
     *
     * @param userId
     * @return
     */
    boolean changeProjUpdateState(Integer userId);

//    Page getCollByPage(Page page);

//    int delAll();

}