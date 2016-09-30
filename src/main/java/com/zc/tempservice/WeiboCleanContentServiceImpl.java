package com.zc.tempservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.tempbean.WeiboCleanContent;
import com.zc.tempdao.WeiboCleanContentDao;

@Service
public class WeiboCleanContentServiceImpl implements WeiboCleanContentService {

    @Autowired
    private WeiboCleanContentDao dao;
    
    @Override
    public List<WeiboCleanContent> getList(Integer pageSize, Integer currentPage,Integer weibo_id) {
        if(pageSize==null)
            pageSize=10000000;
        if(currentPage==null)
            currentPage=1;
        return dao.getList(pageSize, currentPage,weibo_id);
            
    }
   
    @Override
    public Integer getItemCount(Integer weibo_id) {
        return dao.getItemCount(weibo_id);
    }
    
}
