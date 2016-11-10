package com.zc.api;

import com.zc.bean.UserFavoriteSearchItem;
import com.zc.dao.HotTopicPredictionMapper;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.HotTopicPredictionModel;
import com.zc.service.HotTopicPredictionService;
import com.zc.utility.CommonHelper;
import com.zc.utility.response.ApiResultModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/9.
 */
@RestController
@RequestMapping("/api/predict/")
public class HotTopicPredictionApi {

    @Autowired
    private HotTopicPredictionService hotTopicPredictionService;

    /**
     * 前台单击日历时间，发送具体的事件列表
     * @param dateStr
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ApiResultModel getPredictionEventList(
            @RequestParam(value = "dateStr") String dateStr
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            if(StringUtils.isBlank( dateStr )){
                resultModel.setStatusCode( StatusCodeEnum.NOCONTENT );
                return resultModel;
            }
            List<HotTopicPredictionModel> hotTopicEventList = hotTopicPredictionService.getHotTopicEventList(dateStr);
            if ( hotTopicEventList != null && hotTopicEventList.size() > 0 ){
                resultModel.setData( hotTopicEventList );
                resultModel.setStatusCode(StatusCodeEnum.SUCCESS);
            }else{
                resultModel.setStatusCode( StatusCodeEnum.NOCONTENT );
            }
        }catch (Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

}
