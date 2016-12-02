package com.zc.api;/**
 * Created by xyzhuzhou on 2016/12/2 0002 14:13:39.
 */

import com.zc.service.VersionInfoService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xyzhuzhou on 2016/12/2 0002 14:13:39.
 */
@RestController
@RequestMapping("/api/proinfo/")
public class ProjectInfoApi {


    @Autowired
    private VersionInfoService versioninfo;

    /**
     * 获取所有版本信息
     *
     * @return
     */
    @RequestMapping(value = "versions", method = RequestMethod.GET)
    public ApiResultModel getVersions() {


        ApiResultModel result = new ApiResultModel();

        result.data(versioninfo.getColl(null));

        return result;
    }





}
