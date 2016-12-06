package com.zc.dao;

import com.zc.bean.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Notice
 */
public interface NoticeMapper {


    int add(Notice record);

    Notice get(Integer id);

    int update(Notice record);

    int del(Integer id);

    List<Notice> getColl(@Param("top") Integer top);

//    List<Notice> getCollByPage(Page page);

//    int delAll();

}