package com.zc.api;/**
 * Created by xyzhuzhou
 */

import com.zc.bean.TopicTrend;
import com.zc.service.TopicTrendService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * topictrend API 接口
 * Created by xyzhuzhou on 2016/12/2 0002 14:13:39.
 */
@RestController
@RequestMapping("/api/topicTrend/")
public class TopicTrendApi {

    @Autowired
    private TopicTrendService topicTrendService;

    @RequestMapping(value = "history/{id}")
    public ApiResultModel getTopicTrendHistory(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(topicTrendService.getTopicTrendHistory(id));

        return result;
    }

}
