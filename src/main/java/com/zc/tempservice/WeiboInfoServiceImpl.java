package com.zc.tempservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.tempbean.WeiboInfo;
import com.zc.tempdao.WeiboInfoDao;

@Service
public class WeiboInfoServiceImpl implements WeiboInfoService {

    @Autowired
    private WeiboInfoDao dao;
    @Override
    public List<WeiboInfo> getList(Integer pageSize, Integer currentPage) {
        if(pageSize==null)
            pageSize=10000000;
        if(currentPage==null)
            currentPage=1;
        return dao.getList(pageSize, currentPage);
    }

    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }

}
