/** 
* @title TopicInfoService.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月23日 下午5:54:52 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempservice;

import java.util.List;

import com.zc.tempbean.TopicInfo;

public interface TopicInfoService {
     List<TopicInfo>getList(Integer pageSize,Integer currentPage);
     Integer getItemCount();
     
}
