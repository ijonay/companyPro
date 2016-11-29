package com.zc.dao;

import com.zc.bean.DComment;

/**
 * 
 */
public interface DCommentMapper {


    int add(DComment record);

    DComment get(Integer id);

    int update(DComment record);

    int del(Integer id);


}