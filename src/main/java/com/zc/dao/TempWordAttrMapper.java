package com.zc.dao;


import com.zc.bean.TempWordAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface TempWordAttrMapper {


    int add(TempWordAttr record);

    TempWordAttr get(Integer id);

    int update(TempWordAttr record);

    int del(Integer id);

    List<TempWordAttr> getCollByWords(@Param("words") Set<String> words, @Param("frequency") Integer frequency);

//    List<TempWordAttr> getColl(Integer top);
//
//    int delAll();

}