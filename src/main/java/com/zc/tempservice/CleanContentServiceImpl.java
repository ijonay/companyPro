/** 
* @title WeiBoServiceImpl.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月23日 下午2:58:43 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.tempbean.CleanContent;
import com.zc.tempdao.CleanContentDao;

@Service
public class CleanContentServiceImpl implements CleanContentService{

    @Autowired
    private CleanContentDao dao;
    /**
     * 方法说明
     * @param pageSize
     * @param currentPage
     * @return
     * @see com.zc.tempservice.CleanContentService#getList(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<CleanContent> getList(Integer pageSize, Integer currentPage,Integer topic_id) {
        if(pageSize==null)
            pageSize=10000000;
        if(currentPage==null)
            currentPage=1;
        return dao.getList(pageSize, currentPage,topic_id);
            
    }
    /**
     * 方法说明
     * @param topic_id
     * @return
     * @see com.zc.tempservice.CleanContentService#getItemCount(java.lang.Integer)
     */
    @Override
    public Integer getItemCount(Integer topic_id) {
        return dao.getItemCount(topic_id);
    }

}
