package com.zc.service;

import com.zc.bean.UserFavoriteSearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.dao.UserFavoriteSearchItemDao;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/8.
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private UserFavoriteSearchItemDao userFavoriteSearchItemDao;

    @Override
    public boolean add(UserFavoriteSearchItem item){
            return userFavoriteSearchItemDao.add(item) > 0;
    }

    @Override
    public List<UserFavoriteSearchItem> getUserFavoriteSearchItems(Integer userId){
        return userFavoriteSearchItemDao.getUserFavoriteSearchItems(userId);
    }

    @Override
    public boolean cancelSearchItem(Integer id){
             return  userFavoriteSearchItemDao.cancelSearchItem(id) > 0;
    }

    @Override
    public List<UserFavoriteSearchItem> getAllUserFavoriteSearchItems(){
            return userFavoriteSearchItemDao.getAllUserFavoriteSearchItems();
    }
}
