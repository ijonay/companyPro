package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.topicsearch.SearchModel;
import com.zc.service.TopicService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topic")
public class TopicApi extends BaseApi {

    @Autowired
    private TopicService topicService;

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "getlist", method = RequestMethod.POST)
    public ApiResultModel getHotTopic(
            @RequestParam(value = "clueWord") String clueWord,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestBody(required = false) SearchModel searchModel) {
        try {
            if (pageSize <= 0 || currentPage <= 0)
                return new ApiResultModel(null);

            return new ApiResultModel().data(topicService.getListExt(clueWord, searchModel, currentPage, pageSize));

        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR,
                    e.getMessage() + "_" + e.getStackTrace());
        }
    }
}
