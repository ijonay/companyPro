package com.zc.service;

import com.zc.bean.DComment;
import com.zc.dao.DCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DCommentServiceImpl implements DCommentService {

    @Autowired
    private DCommentMapper dCommentMapper;
    
    @Override
    public boolean add(DComment record) {
        return dCommentMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return dCommentMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(DComment record) {
       return dCommentMapper.update(record) > 0;
    }

    @Override
    public DComment get(Integer id) {
        return dCommentMapper.get(id);
    }

}
