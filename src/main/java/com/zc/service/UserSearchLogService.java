package com.zc.service;

import com.zc.bean.UserSearchLog;
import com.zc.model.UserSearchLogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/10.
 */
public interface UserSearchLogService {

    boolean add(UserSearchLog log);
    boolean delete(Integer id);
    List<UserSearchLogModel> getUserSearchLogList(
            @Param("userId") Integer userId,
            @Param("count") Integer count);

}
