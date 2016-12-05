package com.zc.dao;

import com.zc.bean.VersionInfo;
import com.zc.utility.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface VersionInfoMapper {


    int add(VersionInfo record);

    VersionInfo get(Integer id);

    int update(VersionInfo record);

    int del(Integer id);

    List<VersionInfo> getColl(@Param("top") Integer top);

    List<VersionInfo> getCollByPage(Page page);
//
//    int delAll();

}