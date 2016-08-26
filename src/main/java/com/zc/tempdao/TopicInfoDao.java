/** 
* @title TopicInfoDao.java
* @author huyulinhome/huyl@heptax.com
* @date：2016年8月23日 下午6:00:19 
* Copyright 2016 知藏. All right reserved.
* 类说明
*/
package com.zc.tempdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zc.tempbean.TopicInfo;

public interface TopicInfoDao {
    List<TopicInfo>getList(@Param("pageSize")Integer pageSize,@Param("currentPage")Integer currentPage);
    Integer getItemCount();
}
