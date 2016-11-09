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
        boolean result = true;
        try{
            int flag = userFavoriteSearchItemDao.add(item);
            if( flag == 0){
                result = false;
            }
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<UserFavoriteSearchItem> getUserFavoriteSearchItems(Integer userId){
        return userFavoriteSearchItemDao.getUserFavoriteSearchItems(userId);
    }

    @Override
    public boolean cancelSearchItem(Integer id){
        boolean result = true;
        try{
            int flag = userFavoriteSearchItemDao.cancelSearchItem(id);
            if( flag == 0){
                result = false;
            }
        }catch (Exception e){
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
