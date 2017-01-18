package com.zc.service;

import com.zc.model.WxArticleField;
import com.zc.model.WxArticleInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by zhangchengli on 2017/1/17.
 */
public interface WxArticleService {

    List<WxArticleInfoModel> getWxArticleInfoList(
            @Param("pageSize")Integer pageSize,
            @Param("rowStart")Integer rowStart );

    List<WxArticleField> getWxArticleFields();

    List<LinkedHashMap<String, Object>> getStructSearch(List<String> keys);
}
