package com.zc.service;

import com.zc.bean.UserActionLog;
import com.zc.dao.UserActionLogMapper;
import com.zc.utility.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActionLogServiceImpl implements UserActionLogService {

    @Autowired
    private UserActionLogMapper userActionLogMapper;

    @Override
    public boolean add(UserActionLog record) {
        return userActionLogMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return userActionLogMapper.del(id) > 0;
    }

    @Override
    public boolean update(UserActionLog record) {
       return userActionLogMapper.update(record) > 0;
    }

    @Override
    public UserActionLog get(Integer id) {
        return userActionLogMapper.get(id);
    }
   @Override
   public List<UserActionLog> getColl(Integer top) {
       return userActionLogMapper.getColl(top);
   }

    @Override
    public Page getCollByPage(Page page) {

        return page.data(userActionLogMapper.getCollByPage(page));

    }

//    @Override
//    public int delAll() { return userActionLogMapper.delAll(); }

}
