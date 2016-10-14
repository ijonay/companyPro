package com.zc.service;

import com.zc.bean.Users;

public interface UsersService {
    
    
    boolean add(Users record);
    
    boolean del(Integer id);

    boolean update(Users record);
    
    Users get(Integer id);


    Users getByUserName(String userName);
}