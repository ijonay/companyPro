/** 
* @title TopicInfoServiceImpl.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月23日 下午5:59:24 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.tempbean.TopicInfo;
import com.zc.tempdao.TopicInfoDao;

@Service
public class TopicInfoServiceImpl implements TopicInfoService {

    @Autowired
    private TopicInfoDao dao;
    /**
     * 按页取出topic
     * @param pageSize 
     * @param currentPage
     * @return
     * @see com.zc.tempservice.TopicInfoService#getList(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<TopicInfo> getList(Integer pageSize, Integer currentPage) {
        if(pageSize==null)
            pageSize=10000000;
        if(currentPage==null)
            currentPage=1;
        return dao.getList(pageSize, currentPage);
    }

    /**
     * 获取所有topic的数量
     * @return
     * @see com.zc.tempservice.TopicInfoService#getItemCount()
     */
    @Override
    public Integer getItemCount() {
        return dao.getItemCount();
    }

}
