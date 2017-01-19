package com.zc.api;

import com.alibaba.fastjson.JSONObject;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.TopicModel;
import com.zc.model.WxArticleField;
import com.zc.model.WxArticleInfoModel;
import com.zc.model.solrmodel.ArticleSearchModel;
import com.zc.service.WxArticleService;
import com.zc.utility.SolrSearchHelper;
import com.zc.utility.ParamHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@RestController
@RequestMapping("/api/wechat")
public class WechatExploreApi {

    @Autowired
    public WxArticleService wxArticleService;

    @RequestMapping("/wxArticleFields")
    public ApiResultModel getWxArticleFiledList() {
        ApiResultModel result = new ApiResultModel();
        try {
            List<WxArticleField> fieldList = wxArticleService.getWxArticleFields();
            if (!fieldList.isEmpty()) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(fieldList);
            } else {
                result.setStatusCode(StatusCodeEnum.NOCONTENT);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.FAILED);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/structSearch", method = RequestMethod.GET)
    public ApiResultModel getWxArticleTtitleStructSearch(@RequestParam("kw") String keywords) {

        Objects.requireNonNull(keywords);

        ApiResultModel result = new ApiResultModel();

        result.data(wxArticleService.getStructSearch(Arrays.asList(keywords.split(" "))));

        return result;

    }

    @RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
    public ApiResultModel searchArticle(@RequestBody ArticleSearchModel searchModel) throws Exception {

        ParamHelper.lllegalStr(searchModel.getKeywords(), "关键词");

        ApiResultModel result = new ApiResultModel();

        result.data(wxArticleService.getBySearch(searchModel));

        return result;
    }

    @RequestMapping("/wxArticleList")
    public ApiResultModel getWxArticleInfoList(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize
    ) {
        ApiResultModel result = new ApiResultModel();
        try {
            int rowStart = (currentPage - 1) * pageSize;
            List<WxArticleInfoModel> list = wxArticleService.getWxArticleInfoList(pageSize, rowStart);
            if (!list.isEmpty()) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(list);
            } else {
                result.setStatusCode(StatusCodeEnum.NOCONTENT);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.FAILED);
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/wxTopicList")
    public ApiResultModel getKeywordsRelatedTopics(
            @RequestParam(value = "kw", required = true) String kw,
            @RequestParam(value = "count",required = false,defaultValue = "10") Integer count ){
        ApiResultModel result = new ApiResultModel();
        try{
            List<String> termList = SolrSearchHelper.getSolrTerms(kw);
            List<TopicModel> topicList = wxArticleService.getSimilarTopicList(termList, count);
            if(!topicList.isEmpty()){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("topicList", topicList);
                jsonObject.put("termList",termList);
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(jsonObject);
            }else{
                result.setStatusCode(StatusCodeEnum.NOCONTENT);
            }
        }catch (Exception e){
            result.setStatusCode(StatusCodeEnum.FAILED);
            e.printStackTrace();
        }
        return result;
    }

}
