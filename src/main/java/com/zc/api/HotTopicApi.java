package com.zc.api;

import java.util.List;
import java.util.stream.Collectors;

import com.zc.enumeration.StatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.utility.response.ApiResultModel;
import com.zc.model.HotTopicModel;
import com.zc.service.HotTopicService;

@RestController
@RequestMapping("/api/hottopic")
public class HotTopicApi extends BaseApi {
    @Autowired
    private HotTopicService service;

    @RequestMapping("getlist")
    public ApiResultModel getHotTopic(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        int rowStart = (currentPage - 1) * pageSize;
        List<HotTopicModel> list = service.getList(pageSize, rowStart).stream().map(h -> h.getModel()).collect(Collectors.toList());
        return new ApiResultModel(list, StatusCodeEnum.SUCCESS);
    }
}
