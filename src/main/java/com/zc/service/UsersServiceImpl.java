package com.zc.service;

import java.util.List;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bean.Users;
import com.zc.dao.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean add(Users record) {
        return usersMapper.add(record) > 0;
    }

    @Override
    public boolean del(Integer id) {
        return usersMapper.del(id) > 0;
    }

    @Override
    public boolean update(Users record) {
        return usersMapper.update(record) > 0;
    }

    @Override
    public Users get(Integer id) {
        return usersMapper.get(id);
    }

    @Override
    public Users getByUserName(String userName) {
        Users users = usersMapper.getByUserName(userName);
        if (users == null) {
            throw new ServiceException(StatusCodeEnum.SERVER_ERROR, "没有查询到userName=" + userName + "的用户信息");
        }

        return users;
    }

}
