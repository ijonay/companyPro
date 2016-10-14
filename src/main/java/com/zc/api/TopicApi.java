package com.zc.api;

import java.util.List;
import java.util.stream.Collectors;

import com.zc.service.TopicService;
import com.zc.utility.ListHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.utility.response.ApiResultModel;
import com.zc.model.TopicModel;
import com.zc.service.WordService;

@RestController
@RequestMapping("/api/topic")
public class TopicApi extends BaseApi {

    @Autowired
    private TopicService topicService;

    @RequestMapping("getlist")
    public ApiResultModel getHotTopic(
            @RequestParam(value = "clueWord") String clueWord,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
        try {
            if (pageSize <= 0 || currentPage <= 0)
                return new ApiResultModel(null);

            List<TopicModel> topics = topicService.getListExt(clueWord, currentPage, pageSize);
            ApiResultModel resultModel = new ApiResultModel();

            if (ListHelper.isEmpty(topics)) {
                resultModel.setStatusCode(StatusCodeEnum.NOCONTENT);
            } else {
                resultModel.setData(topics);
            }

            return resultModel;

        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR,
                    e.getMessage() + "_" + e.getStackTrace());
        }
    }
}
