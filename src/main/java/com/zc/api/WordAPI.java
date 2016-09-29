package com.zc.api;

import java.util.List;

import com.zc.enumeration.StatusCodeEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.bean.Topic;
import com.zc.utility.response.ApiResultModel;
import com.zc.model.TopicModel;
import com.zc.model.VertexEdgeModel;
import com.zc.service.TopicService;
import com.zc.service.WordService;
import com.zc.tempbean.TopicCleanContent;
import com.zc.tempservice.TopicCleanContentService;
import com.zc.utility.ResourceDict;

/**
 * Created by 张镇强 on 2016/7/8 10:54.
 */
@RestController
@RequestMapping("/api/")
public class WordAPI {
    @Autowired
    private WordService wordService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicCleanContentService weiboService;
    private WordAPI() {
    }

    // private static final HashMap<String, WordModel> MODEL_DICT = new
    // HashMap<String, WordModel>() {{
    // put("warcraft", new WordModel("warcraft", "魔兽世界",
    // "classpath:dataset/vectors.bin.Douban.WarCraft"));
    // put("ring", new WordModel("ring", "指环王",
    // "classpath:dataset/vectors.bin.Douban.Ring"));
    // put("crazyanimal", new WordModel("crazyanimal", "疯狂动物城",
    // "classpath:dataset/vectors.bin.Douban.CrazyAnimal"));
    // }};

    @RequestMapping("models")
    public ApiResultModel getModels() {
        ApiResultModel resultModel = new ApiResultModel(
                ResourceDict.MODEL_DICT.values());

        return resultModel;
    }

    @RequestMapping("words/{model}")
    public ApiResultModel getWords(
            @PathVariable("model") String model,
            @RequestParam(value = "clueWord") String clueWord,
            @RequestParam(value = "topN", required = false, defaultValue = "40") int topN,
            @RequestParam(value = "relevancy", required = false, defaultValue = "0.5") float relevancy,
            @RequestParam(value = "length", required = false, defaultValue = "3") int length) {
        if (!ResourceDict.MODEL_DICT.containsKey(model)) {
            return new ApiResultModel(StatusCodeEnum.CLIENT_ERROR, "没有找到所要的模型");
        }

        try {
            VertexEdgeModel words = wordService.getWords(model, clueWord, topN,
                    relevancy, length);
            return new ApiResultModel(words);
        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR, e.getMessage() + "_"
                    + e.getStackTrace());
        }
    }

    @RequestMapping("words/topic")
    public ApiResultModel getTopics(
            @RequestParam(value = "clueWord") String clueWord,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage) {
        try {
            List<TopicModel> result = wordService
                    .getTopicSimilarity(clueWord);
            return new ApiResultModel(result);

        } catch (Exception e) {
            return new ApiResultModel(StatusCodeEnum.SERVER_ERROR, e.getMessage() + "_"
                    + e.getStackTrace());
        }
    }

    @RequestMapping("words/test")
    public ApiResultModel test() {
        Topic topic = new Topic();
        topic.setTitle("xixi");
        topic.setCoordinate("hahhhahahh");
        topicService.add(topic);
        return new ApiResultModel(null);
    }
    
    @RequestMapping("words/test1")
    public ApiResultModel test1() {
        List<TopicCleanContent>list=weiboService.getList(10, 10,100);
        return new ApiResultModel(list);
    }

}
