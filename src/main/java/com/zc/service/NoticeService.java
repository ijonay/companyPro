package com.zc.service;

import com.zc.bean.Notice;

import java.util.List;

public interface NoticeService {
    
    
    boolean add(Notice record);
    
    boolean del(Integer id);

    boolean update(Notice record);
    
    Notice get(Integer id);


    List<Notice> getColl(Integer top);

//    Page getCollByPage(Page page);

//    int delAll();

}