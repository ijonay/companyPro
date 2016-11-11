package com.zc.api;

import com.zc.bean.UserFavoriteSearchItem;
import com.zc.bean.UserSearchLog;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.UserSearchLogModel;
import com.zc.service.SearchItemService;
import com.zc.service.UserSearchLogService;
import com.zc.utility.CommonHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhangcl on 2016/11/10.
 */
@RestController
@RequestMapping("/api/searchLog")
public class UserSearchLogApi extends BaseApi{


    @Autowired
    private UserSearchLogService userSearchLogService;

    @RequestMapping(value = "add")
    public ApiResultModel addUserSearchLog(
            @RequestParam(value = "keyword") String keyword,HttpSession session
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            UserSearchLog userSearchLog = new UserSearchLog();
            userSearchLog.setKeyword( keyword );
            userSearchLog.setUserId(CommonHelper.getCurrentUserId());
            userSearchLog.setSessionId( session.getId() );
            userSearchLogService.add( userSearchLog );
            resultModel.setData(userSearchLog.getId() );
            resultModel.code(StatusCodeEnum.SUCCESS);
        }catch (Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

    @RequestMapping(value = "delete")
    public ApiResultModel deleteUserSearchLog(
            @RequestParam(value = "id") Integer id
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            if( userSearchLogService.delete(id) ){
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

    @RequestMapping(value = "list")
    public ApiResultModel getUserSearchLogList(
            @RequestParam(value = "count", required = false, defaultValue = "5") int count
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            List<UserSearchLogModel> list =
                    userSearchLogService.getUserSearchLogList(
                            CommonHelper.getCurrentUserId(), count);
            resultModel.setData(list);
            resultModel.setStatusCode(StatusCodeEnum.SUCCESS);
        }catch(Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }
}
