package com.zc.service;

import com.zc.bean.UserFavoriteSearchItem;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/8.
 */
public interface SearchItemService {

    UserFavoriteSearchItem add(UserFavoriteSearchItem item);

    List<UserFavoriteSearchItem> getUserFavoriteSearchItems(Integer userId);

    boolean cancelSearchItem(Integer id);
}
