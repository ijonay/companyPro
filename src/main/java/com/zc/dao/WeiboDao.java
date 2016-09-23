package com.zc.dao;

import com.zc.bean.Weibo;

import java.util.List;

/**
 * Created by 张镇强 on 2016/9/18 12:42.
 */
public interface WeiboDao {
    Integer add(Weibo entity);

    Weibo get(Integer id);

    Integer del(Integer id);

    Integer update(Weibo entity);

    /**
     * 验证方法
     */
    List<Weibo> getAll();
}