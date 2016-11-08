package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.TopicModel;
import com.zc.model.topicsearch.SearchModel;
import com.zc.service.TopicService;
import com.zc.utility.ListHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestBody SearchModel searchModel) {
        try {
            if (pageSize <= 0 || currentPage <= 0)
                return new ApiResultModel(null);

            List<TopicModel> topics = topicService.getListExt(clueWord, searchModel, currentPage, pageSize);
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
