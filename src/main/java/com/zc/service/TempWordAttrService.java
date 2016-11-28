package com.zc.service;


import com.zc.bean.TempWordAttr;

import java.util.List;

public interface TempWordAttrService {


    boolean add(TempWordAttr record);

    boolean del(Integer id);

    boolean update(TempWordAttr record);

    TempWordAttr get(Integer id);

    List<TempWordAttr> getCollByWords(List<String> words);

//    List<TempWordAttr> getColl(Integer top);
//
//    int delAll();

}