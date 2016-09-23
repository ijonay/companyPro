package com.zc.service;

import com.zc.BaseTest;
import com.zc.model.path.NodeRelations;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 张镇强 on 2016/9/23 16:06.
 */
public class PathServiceTest extends BaseTest {
    @Autowired
    private PathService pathService;

    @Test
    public void testGetRelations() {
        NodeRelations result = pathService.getRelations("赵丽颖", "李易峰");
        System.out.println(result);
    }
}
