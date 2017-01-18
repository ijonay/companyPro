package com.zc.service;

import com.zc.model.solrmodel.ArticleModel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by zhangchengli on 2017/1/17.
 */
public interface WxArticleService {

//    List<WxArticleInfoModel> getWxWxArticleInfoList();


    List<LinkedHashMap<String, Object>> getStructSearch(List<String> keys);


    List<ArticleModel> getBySearch();

}
