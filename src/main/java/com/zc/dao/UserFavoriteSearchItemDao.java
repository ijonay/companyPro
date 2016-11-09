package com.zc.dao;

import com.zc.bean.UserFavoriteSearchItem;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/8.
 */
public interface UserFavoriteSearchItemDao {

    int add(UserFavoriteSearchItem item);

    List<UserFavoriteSearchItem> getUserFavoriteSearchItems(Integer userId);

    int cancelSearchItem(Integer id);
}
