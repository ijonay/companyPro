package com.zc.api;

import com.zc.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 张镇强 on 2016/9/13 16:11.
 */
@RestController
@RequestMapping("/api/")
public class TestApi {
    @Autowired
    private WordService wordService;

    @RequestMapping("test")
    public void test() {
    }
}
