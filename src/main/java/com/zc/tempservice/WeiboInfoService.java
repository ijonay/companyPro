package com.zc.tempservice;

import java.util.List;

import com.zc.tempbean.WeiboInfo;

public interface WeiboInfoService {
    List<WeiboInfo>getList(Integer pageSize,Integer currentPage);
    Integer getItemCount();
}
