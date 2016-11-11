package com.zc.api;

import com.zc.bean.Topic;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.TopicModel;
import com.zc.model.topicsearch.SearchModel;
import com.zc.service.TopicService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topic")
public class TopicApi extends BaseApi {

    @Autowired
    private TopicService topicService;

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


    @RequestMapping(value = "hottopic/{count}", method = RequestMethod.GET)
    public ApiResultModel getHotTopic(@PathVariable("count") Integer count) {

        try {

            Objects.requireNonNull(count);

            List<Topic> list = topicService.getHotTopic(count);
            List<TopicModel> result = new ArrayList<>();

            if (!Objects.isNull(list)) {

                result = list.stream().map(p -> p.getModel()).collect(Collectors.toList());

            }

            return new ApiResultModel().data(result);

        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR,
                    e.getMessage() + "_" + e.getStackTrace());
        }
    }

    @RequestMapping(value = "percentage/{topicId}", method = RequestMethod.GET)
    public ApiResultModel getTopicPercentage(@PathVariable("topicId") Integer topicId) {

        Objects.requireNonNull(topicId);

        ApiResultModel result = new ApiResultModel();

        result.data(topicService.getTopicPercentage(topicId));


        return result;
    }
}
