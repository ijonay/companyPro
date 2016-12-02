package com.zc.api;/**
 * Created by xyzhuzhou on 2016/12/2 0002 14:13:39.
 */

import com.zc.bean.VersionInfo;
import com.zc.service.VersionInfoService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @RequestMapping(value = "version/{id}", method = RequestMethod.GET)
    public ApiResultModel getVersionInfo(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(versioninfo.get(id));

        return result;
    }

    @RequestMapping(value = "version", method = RequestMethod.POST)
    public ApiResultModel addVersion(@RequestBody VersionInfo model) {


        ApiResultModel result = new ApiResultModel();

        result.data(versioninfo.add(model));

        return result;
    }

    @RequestMapping(value = "version/{id}", method = RequestMethod.DELETE)
    public ApiResultModel delVersion(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(versioninfo.del(id));

        return result;
    }


}
