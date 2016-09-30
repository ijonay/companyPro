package com.zc.service;

import com.zc.bean.DReview;
import com.zc.dao.DReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DReviewServiceImpl implements DReviewService {

    @Autowired
    private DReviewMapper dReviewMapper;
    
    @Override
    public boolean add(DReview record) {
        return dReviewMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return dReviewMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(DReview record) {
       return dReviewMapper.update(record) > 0;
    }

    @Override
    public DReview get(Integer id) {
        return dReviewMapper.get(id);
    }

}
