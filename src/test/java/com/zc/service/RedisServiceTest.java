package com.zc.service;

import com.zc.BaseTest;
import com.zc.model.WordRedisModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张镇强 on 2016/8/29 17:16.
 */
public class RedisServiceTest extends BaseTest {
    @Autowired
    RedisServiceImpl redisService;

    @Test
    public void addWordToRedisTest() {
        List<String> urlList = new ArrayList<String>();
        urlList.add("百度");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("百度");
        urlList.add("谷歌");
        urlList.add("谷歌");

//        redisService.add();
//        for (String item : urlList) {
//            redisService.test("topicanalysis:words:搜索引擎", item);
//        }
    }

    @Test
    public void readRedisTest() {
        redisService.get("");
    }
}
