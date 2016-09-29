package com.zc.service;

import com.zc.bean.DReview;

public interface DReviewService {
    
    
    boolean add(DReview record);
    
    boolean del(Integer id);

    boolean update(DReview record);
    
    DReview get(Integer id);


}