package com.zc.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.bean.Weibo;


public interface WeiboDao {
    Integer batchInsert(@Param("list") List<Weibo> list);

    List<Weibo> getList(@Param("pageSize") Integer pageSize,
            @Param("rowStart") Integer rowStart);

    Integer getItemCount();

    Integer batchUpdate(@Param("list") List<Weibo> list);
}

