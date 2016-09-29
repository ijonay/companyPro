package com.zc.tempdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.tempbean.WeiboInfo;

public interface WeiboInfoDao {
    List<WeiboInfo>getList(@Param("pageSize")Integer pageSize,@Param("currentPage")Integer currentPage);
    Integer getItemCount();
}
