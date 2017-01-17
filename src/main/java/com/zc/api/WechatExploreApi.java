package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.WxArticleField;
import com.zc.service.WxArticleFieldService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangchengli on 2017/1/17.
 */
@RestController
@RequestMapping("/api/wechat")
public class WechatExploreApi {

    @Autowired
    public WxArticleFieldService wxArticleFieldService;

    @RequestMapping("/wxArticleFields")
    public ApiResultModel getWxArticleFiledList(){
        ApiResultModel result = new ApiResultModel();
        try {
            List<WxArticleField> fieldList = wxArticleFieldService.getWxArticleFields();
            if (!fieldList.isEmpty()) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
                result.setData(fieldList);
            } else {
                result.setStatusCode(StatusCodeEnum.FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
