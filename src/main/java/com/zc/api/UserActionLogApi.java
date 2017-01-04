package com.zc.api;/**
 * Created by xyzhuzhou
 */

import com.zc.bean.UserActionLog;
import com.zc.service.UserActionLogService;
import com.zc.utility.CommonHelper;
import com.zc.utility.page.Page;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

/**
 * UserActionLog API 接口
 * Created by xyzhuzhou on 2016/12/2 0002 14:13:39.
 */
@RestController
@RequestMapping("/api/userActionLog/")
public class UserActionLogApi {


    @Autowired
    private UserActionLogService userActionLogService;

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ApiResultModel getUserActionLogsByPage(@RequestParam(name = "PageIndex", defaultValue = "1") Integer
                                                          PageIndex,
                                                  @RequestParam(name = "PageSize", defaultValue = "10") Integer
                                                          PageSize) {


        ApiResultModel result = new ApiResultModel();

        Page page = new Page(PageIndex, PageSize);

        result.data(userActionLogService.getCollByPage(page));

        return result;
    }

    @RequestMapping(value = "userActionLogs", method = RequestMethod.GET)
    public ApiResultModel getUserActionLogs() {


        ApiResultModel result = new ApiResultModel();

        result.data(userActionLogService.getColl(null));

        return result;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ApiResultModel getUserActionLog(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(userActionLogService.get(id));

        return result;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ApiResultModel addOrUpdateUserActionLog(@RequestBody UserActionLog model) {


        ApiResultModel result = new ApiResultModel();

        if (Objects.nonNull(model.getId()) && model.getId() > 0) {
            result.data(userActionLogService.update(model));
        } else {

            model.setCreateTime(new Date());

            model.setUserId(CommonHelper.getCurrentUserId());
            model.setUserName(CommonHelper.getCurrentUserLoginName());
            result.data(userActionLogService.add(model));
        }
        return result;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ApiResultModel delUserActionLog(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(userActionLogService.del(id));

        return result;
    }


}
