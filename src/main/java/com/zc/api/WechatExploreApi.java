package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.TopicModel;
import com.zc.model.WxArticleField;
import com.zc.model.WxArticleInfoModel;
import com.zc.service.WxArticleService;
import com.zc.utility.SolrSearchHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/searchArticle", method = RequestMethod.GET)
    public ApiResultModel searchArticle() {

        ApiResultModel result = new ApiResultModel();


        return result;
    }

    @RequestMapping("/wxArticleList")
    public ApiResultModel getWxArticleInfoList(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "100") int pageSize
    ){
        ApiResultModel result = new ApiResultModel();
        try{
            int rowStart = (currentPage - 1) * pageSize;
            List<WxArticleInfoModel> list = wxArticleService.getWxArticleInfoList(pageSize,rowStart);
            if(!list.isEmpty()){
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(list);
            }else{
                result.setStatusCode(StatusCodeEnum.NOCONTENT);
            }
        }catch(Exception e){
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
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(topicList);
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
