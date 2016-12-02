package com.zc.service;

import com.zc.bean.VersionInfo;

import java.util.List;

public interface VersionInfoService {


    boolean add(VersionInfo record);

    boolean del(Integer id);

    boolean update(VersionInfo record);

    VersionInfo get(Integer id);

    List<VersionInfo> getColl(Integer top);

//    int delAll();

}