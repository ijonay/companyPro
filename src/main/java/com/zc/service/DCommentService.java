package com.zc.service;

import com.zc.bean.DComment;

public interface DCommentService {
    
    
    boolean add(DComment record);
    
    boolean del(Integer id);

    boolean update(DComment record);
    
    DComment get(Integer id);


}