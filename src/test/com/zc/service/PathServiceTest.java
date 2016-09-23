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
        NodeRelations result = pathService.getRelations("王宝强", "唐人");
        NodeRelations result1 = pathService.getRelations("王宝强", "唐人");
        NodeRelations result2 = pathService.getRelations("王宝强", "陈思诚");
        NodeRelations result3 = pathService.getRelations("王宝强", "陈思成");
        NodeRelations result4 = pathService.getRelations("王宝强", "宝宝");
        NodeRelations result5 = pathService.getRelations("王宝强", "宝强");
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    }
}
