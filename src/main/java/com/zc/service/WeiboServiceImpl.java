package com.zc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.bean.Weibo;
import com.zc.dao.WeiboDao;

@Service
public class WeiboServiceImpl implements WeiboService{

    @Autowired
    private WeiboDao dao;
    
    @Override
    public Integer batchInsert(List<Weibo> list) {
        if (list == null || list.size() == 0) return 0;
        return dao.batchInsert(list);
    }

    @Override
    public List<Weibo> getList(Integer pageSize, Integer currentPage) {
       return dao.getList(pageSize, currentPage);
    }

    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }


    @Override
    public Integer batchUpdate(List<Weibo> list) {
        if(list==null||list.size()==0)return 0;
        return dao.batchUpdate(list);
    }
    
    public void writeWeibo(){
        
    }
}
