package com.zc.service;

import com.zc.bean.UserSearchLog;
import com.zc.dao.UserSearchLogMapper;
import com.zc.model.UserSearchLogModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/10.
 */
@Service
public class UserSearchLogServiceImpl implements UserSearchLogService{

    @Autowired
    private UserSearchLogMapper userSearchLogMapper;

    @Override
    public boolean add(UserSearchLog log){
            return userSearchLogMapper.add(log) > 0;
    }

    @Override
    public boolean delete(Integer id){
            return  userSearchLogMapper.delete(id) > 0;
    }

    @Override
    public List<UserSearchLogModel> getUserSearchLogList(
            @Param("userId") Integer userId, @Param("count") Integer count){
            return userSearchLogMapper.getUserSearchLogList(userId, count);
    }

}
