package com.zc.api;

import com.zc.service.TopicService;
import com.zc.service.WordService;
import com.zc.utility.response.ApiResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xyzhuzhou on 2016/10/26 0026 16:53:37.
 */
@RestController
@RequestMapping("/api/admin/")
public class AdminApi extends BaseApi {

    private final Logger logger = LoggerFactory.getLogger(AdminApi.class);

    @Autowired
    private WordService wordService;
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "updateWordToRedis", method = RequestMethod.POST)
    public ApiResultModel updateWordVectorstoRedis() {

        wordService.cache_UpdateWordVectors();

        return new ApiResultModel();


    }


    @RequestMapping(value = "updateWordToRediscoll", method = RequestMethod.POST)
    public ApiResultModel updateWordVectorsColltoRedis() {

        wordService.cache_UpdateWordVerctorsToColl();

        return new ApiResultModel();


    }

    @RequestMapping(value = "updateTopicVectorCollToRedis", method = RequestMethod.POST)
    public ApiResultModel updateTopicVectorCollToRedis() {

        topicService.cache_UpdateTopicVerctorToColl();

        return new ApiResultModel();


    }

    @RequestMapping(value = "updateTopicVertorsToRedis", method = RequestMethod.POST)
    public ApiResultModel updateTopicVertorsToRedis() {

        topicService.cache_UpdateTopicVerctors();

        return new ApiResultModel();


    }


}
