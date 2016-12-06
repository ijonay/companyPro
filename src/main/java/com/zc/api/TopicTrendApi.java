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

    @RequestMapping(value = "topicTrends", method = RequestMethod.GET)
    public ApiResultModel getTopicTrends() {

        ApiResultModel result = new ApiResultModel();

        result.data(topicTrendService.getColl(null));

        return result;
    }

    @RequestMapping(value = "topicTrend/{id}", method = RequestMethod.GET)
    public ApiResultModel getTopicTrend(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(topicTrendService.get(id));

        return result;
    }


    @RequestMapping(value = "topicTrend", method = RequestMethod.POST)
    public ApiResultModel addOrUpdateTopicTrend(@RequestBody TopicTrend model) {


        ApiResultModel result = new ApiResultModel();

        if (Objects.nonNull(model.getId()) && model.getId() > 0) {

            result.data(topicTrendService.update(model));
        } else {

            result.data(topicTrendService.add(model));
        }
        return result;
    }


    @RequestMapping(value = "topicTrend/{id}", method = RequestMethod.DELETE)
    public ApiResultModel delTopicTrend(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(topicTrendService.del(id));

        return result;
    }

    @RequestMapping(value = "history/{id}")
    public ApiResultModel getTopicTrendHistory(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        ApiResultModel result = new ApiResultModel();

        result.data(topicTrendService.getTopicTrendHistory(id));

        return result;
    }

}
