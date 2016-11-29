package com.zc.service;

import java.util.List;

import com.zc.bean.Weibo;

public interface WeiboService {

    Integer batchInsert(List<Weibo> list);

    List<Weibo> getList(Integer pageSize, Integer rowStart);

    Integer getItemCount();

    Integer batchUpdate(List<Weibo> list);
}
