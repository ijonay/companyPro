/** 
* @title WeiBoService.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月23日 下午2:53:37 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempservice;

import java.util.List;

import com.zc.tempbean.TopicCleanContent;

public interface TopicCleanContentService {
    List<TopicCleanContent>getList(Integer pageSize,Integer currentPage,Integer topic_id);
    Integer getItemCount(Integer topic_id);
}
