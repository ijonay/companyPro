package com.zc.tempservice;

import java.util.List;

import com.zc.tempbean.WeiboCleanContent;

public interface WeiboCleanContentService {
    List<WeiboCleanContent>getList(Integer pageSize,Integer currentPage,Integer weibo_id);
    Integer getItemCount(Integer weibo_id);
}
