package com.zc.dao;

import com.zc.bean.DReview;

/**
 * 
 */
public interface DReviewMapper {


    int add(DReview record);

    DReview get(Integer id);

    int update(DReview record);

    int del(Integer id);


}