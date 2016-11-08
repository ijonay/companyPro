package com.zc.service;

import com.zc.BaseTest;
import com.zc.model.TopicModel;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 张镇强 on 2016/10/10 15:28.
 */
public class TopicServiceTest extends BaseTest {
    @Autowired
    private TopicService topicService;

    @Test
    public void getAllCoordinatesTest() {
        HashMap<Integer, float[]> coordinates = topicService.getAllCoordinates();
        Assert.assertNotNull(coordinates);
    }

    @Test
    public void getListExtTest() {
        List<TopicModel> topics = topicService.getListExt("王宝强", null, 1, 20);
        System.out.println(topics);
    }
}
