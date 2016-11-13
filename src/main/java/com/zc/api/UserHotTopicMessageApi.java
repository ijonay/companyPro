package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.HotTopicPredictionModel;
import com.zc.model.TopicModel;
import com.zc.model.UserRecommendedTopicModel;
import com.zc.service.UserRecommendedTopicsService;
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
 * Created by zhangxcl on 2016/11/12.
 */
@RestController
@RequestMapping("/api/hotTopicMessage/")
public class UserHotTopicMessageApi extends BaseApi{

    @Autowired
    private UserRecommendedTopicsService userRecommendedTopicsService;

    @RequestMapping(value = "list")
    public ApiResultModel getPredictionEventList(
            @RequestParam(value = "count", required = false, defaultValue = "5") int count
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            List<UserRecommendedTopicModel> list = userRecommendedTopicsService.getUserHotTopicMessageList(
                    CommonHelper.getCurrentUserId(), count);
            if (list!=null && list.size() > 0){
                resultModel.setData(list);
                resultModel.setStatusCode( StatusCodeEnum.SUCCESS );
            }else{
                resultModel.setStatusCode( StatusCodeEnum.NOCONTENT );
            }
        }catch (Exception e){
            resultModel.setStatusCode( StatusCodeEnum.SERVER_ERROR );
            e.printStackTrace();
        }
        return resultModel;
    }

    @RequestMapping(value = "detail")
    public ApiResultModel getTopicMessageDetail(
            @RequestParam(value = "id") Integer id){
        ApiResultModel resultModel = new ApiResultModel();

        try{
            TopicModel topicModel = userRecommendedTopicsService.getTopicMessageDetail(id);
            if (topicModel != null){
                resultModel.setData(topicModel);
                resultModel.setStatusCode( StatusCodeEnum.SUCCESS );
            }else{
                resultModel.setStatusCode( StatusCodeEnum.NOCONTENT );
            }
        }catch (Exception e){
            resultModel.setStatusCode( StatusCodeEnum.SERVER_ERROR );
            e.printStackTrace();
        }
        return resultModel;

    }

    @RequestMapping(value = "del")
    public ApiResultModel deleteUserHotTopicMessage(
            @RequestParam(value = "id") Integer id
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            if( userRecommendedTopicsService.del(id) ){
                resultModel.code(StatusCodeEnum.SUCCESS);
            }else{
                resultModel.code(StatusCodeEnum.FAILED);
            }
        }catch (Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

    @RequestMapping(value = "delAll")
    public ApiResultModel deleteUserAllHotTopicMessage(){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            if( userRecommendedTopicsService.delAll( CommonHelper.getCurrentUserId() ) ){
                resultModel.code(StatusCodeEnum.SUCCESS);
            }else{
                resultModel.code(StatusCodeEnum.FAILED);
            }
        }catch (Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

}
