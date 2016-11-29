/** 
* @title Topic.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月17日 下午12:05:31 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.bean.HotTopic;

public interface HotTopicDao {
     Integer add(HotTopic topic);
     
     Integer del(Integer id);
     
     Integer update(HotTopic topic);
     
     HotTopic get(Integer id);
     
     Integer batchInsert(@Param("list")List<HotTopic>list);
     
     List<HotTopic>getList(@Param("pageSize")Integer pageSize,@Param("rowStart")Integer rowStart);
     Integer getItemCount();
     
     Integer batchUpdate(@Param("list")List<HotTopic>list);
}
