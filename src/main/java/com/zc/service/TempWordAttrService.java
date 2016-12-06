package com.zc.service;


import com.zc.bean.TempWordAttr;

public interface TempWordAttrService {


    boolean add(TempWordAttr record);

    boolean del(Integer id);

    boolean update(TempWordAttr record);

    TempWordAttr get(Integer id);


//    List<TempWordAttr> getColl(Integer top);
//
//    int delAll();

}