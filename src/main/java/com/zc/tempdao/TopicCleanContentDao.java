/** 
* @title WeiBo.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月17日 下午12:05:31 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.tempbean.TopicCleanContent;


public interface TopicCleanContentDao {
     List<TopicCleanContent> getList(@Param("pageSize")Integer pageSize,@Param("currentPage")Integer currentPage,
             @Param("topic_id")Integer topic_id);
     Integer getItemCount(@Param("topic_id")Integer topic_id);
}
