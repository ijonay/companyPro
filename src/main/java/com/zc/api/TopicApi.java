package com.zc.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.bean.Topic;
import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.ApiResultModel;
import com.zc.model.HotTopicModel;
import com.zc.model.TopicModel;
import com.zc.service.TopicService;
import com.zc.service.WordService;

@RestController
@RequestMapping("/api/topic")
public class TopicApi {

    @Autowired
    private WordService wordService;

    @RequestMapping("getlist")
    public ApiResultModel getHotTopic(
            @RequestParam(value = "clueWord") String clueWord,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
        try {
            if (pageSize <= 0 || currentPage <= 0)
                return new ApiResultModel(null);
            List<TopicModel> result = wordService.getTopicSimilarity(clueWord)
                    .stream().map(m -> {
                        m.setCoordinate(null);
                        return m;
                    }).skip((currentPage - 1) * pageSize).limit(pageSize)
                    .collect(Collectors.toList());
            return new ApiResultModel(result);

        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR,
                    e.getMessage() + "_" + e.getStackTrace());
        }
    }
}
