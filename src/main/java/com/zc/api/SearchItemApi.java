package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.CommonHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zc.service.SearchItemService;
import com.zc.bean.UserFavoriteSearchItem;

import java.util.List;

/**
 * Created by zhangcl on 2016/11/8.
 */
@RestController
@RequestMapping("/api/searchItem")
public class SearchItemApi  extends BaseApi{

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("add")
    public ApiResultModel addSearchItem(
            @RequestParam(value = "searchWords") String searchWords
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            UserFavoriteSearchItem searchItem = new UserFavoriteSearchItem();
            searchItem.setUserId(CommonHelper.getCurrentUserId());
            searchItem.setWords(searchWords);
            boolean flag = searchItemService.add( searchItem );
            if(flag) {
                resultModel.code(StatusCodeEnum.SUCCESS);
            }else {
                resultModel.code(StatusCodeEnum.FAILED);
            }
        }catch (Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

    @RequestMapping("list")
    public ApiResultModel getUserFavoriteSearchItems(){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            List<UserFavoriteSearchItem> list = searchItemService.getUserFavoriteSearchItems( CommonHelper.getCurrentUserId() );
            resultModel.setData(list);
            resultModel.setStatusCode(StatusCodeEnum.SUCCESS);
        }catch(Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return resultModel;
    }

    @RequestMapping("cancel")
    public ApiResultModel cancelSearchItem(
            @RequestParam(value = "id") Integer id
    ){
        ApiResultModel resultModel = new ApiResultModel();
        try{
            boolean flag = searchItemService.cancelSearchItem( id );
            if(flag){
                resultModel.setStatusCode(StatusCodeEnum.SUCCESS);
            }else{
                resultModel.setStatusCode(StatusCodeEnum.FAILED);
            }
        }catch(Exception e){
            resultModel.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }

        return resultModel;
    }

}
