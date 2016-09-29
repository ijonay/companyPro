package com.zc.tempdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.tempbean.WeiboCleanContent;

public interface WeiboCleanContentDao {
    List<WeiboCleanContent> getList(@Param("pageSize")Integer pageSize,@Param("currentPage")Integer currentPage,
            @Param("weibo_id")Integer weibo_id);
    Integer getItemCount(@Param("weibo_id")Integer weibo_id);
}
