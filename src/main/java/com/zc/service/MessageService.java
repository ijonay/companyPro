package com.zc.service;

import com.zc.bean.Message;
import java.util.List;

public interface MessageService {
    
    
    boolean add(Message record);
    
    boolean del(Integer id);

    boolean update(Message record);
    
    Message get(Integer id);


    List<Message> getColl(Integer top);

//    Page getCollByPage(Page page);

//    int delAll();

}