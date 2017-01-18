package com.zc.dao;

import com.zc.model.WxArticleInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxArticleInfoMapper {

    List<WxArticleInfoModel> getWxArticleList(
            @Param("pageSize")Integer pageSize,
            @Param("rowStart")Integer rowStart );

}