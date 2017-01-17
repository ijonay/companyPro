package com.zc.api;

import com.zc.service.WxArticleFieldService;
import com.zc.service.WxArticleService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@RestController
@RequestMapping("/api/wechat")
public class WechatExploreApi {

    @Autowired
    public WxArticleFieldService wxArticleFieldService;
    @Autowired
    public WxArticleService wxArticleService;

    @RequestMapping("/wxArticleFields")
    public ApiResultModel getWxArticleFiledList() {
        ApiResultModel result = new ApiResultModel();
//        try {
//            List<WxArticleField> fieldList = wxArticleFieldService.getWxArticleFields();
//            if (!fieldList.isEmpty()) {
//                result.setStatusCode(StatusCodeEnum.SUCCESS);
//                result.setData(fieldList);
//            } else {
//                result.setStatusCode(StatusCodeEnum.FAILED);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

}
