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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


        if (pageSize <= 0 || currentPage <= 0)
            return new ApiResultModel(null);

        return new ApiResultModel().data(topicService.getListExt(clueWord, searchModel, currentPage, pageSize));

    }

    @RequestMapping(value = "getBySearchModel", method = RequestMethod.POST)
    public ApiResultModel getBySearchModel(@RequestParam(value = "size", required = false, defaultValue = "100")
                                                   int size,
                                           @RequestBody(required = false) SearchModel
                                                   searchModel) {


        ApiResultModel result = new ApiResultModel();

        result.data(topicService.getBySearchModel(searchModel, size > 100 ? 100 : size));

        return result;
    }


    /**
     * 获取相似热点推荐(默认3个)
     *
     * @param id
     * @param count
     * @return
     */
    @RequestMapping(value = "getSimilarTopicList", method = RequestMethod.GET)
    public List<TopicModel> getTopHotTopic(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "count", required = false, defaultValue = "3") int count
    ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("count", count);
        return topicService.getTopHotTopic(map);

    }


    @RequestMapping(value = "hottopic/{count}", method = RequestMethod.GET)
    public ApiResultModel getHotTopic(@PathVariable("count") Integer count) {

        try {

            Objects.requireNonNull(count);

            List<Topic> list = null;

            if (count == 10) {
                list = topicService.getRandomHotTopic(30, 10);

            } else {
                list = topicService.getHotTopic(count);
            }
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

    @RequestMapping(value = "inactive")
    public ApiResultModel inactiveTopic(@RequestParam("id")
                                                Integer id) {
        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();
        try {
            if (topicService.inactiveTopic(id)) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
            } else {
                result.setStatusCode(StatusCodeEnum.FAILED);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "active")
    public ApiResultModel activeTopic(@RequestParam("id")
                                              Integer id) {
        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        try {
            if (topicService.activeTopic(id)) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
            } else {
                result.setStatusCode(StatusCodeEnum.FAILED);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "applymanual")
    public ApiResultModel applyTopicManulStatus(@RequestParam("id")
                                                        Integer id) {
        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();
        try {
            if (topicService.applyManual(id)) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
            } else {
                result.setStatusCode(StatusCodeEnum.FAILED);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "cancelmanual")
    public ApiResultModel cancelTopicManulStatus(@RequestParam("id")
                                                         Integer id) {
        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();
        try {
            if (topicService.cancelManual(id)) {
                result.setStatusCode(StatusCodeEnum.SUCCESS);
            } else {
                result.setStatusCode(StatusCodeEnum.FAILED);
            }
        } catch (Exception e) {
            result.setStatusCode(StatusCodeEnum.SERVER_ERROR);
            e.printStackTrace();
        }
        return result;
    }
}
