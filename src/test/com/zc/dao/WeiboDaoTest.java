package com.zc.dao;

import com.zc.BaseTest;
import com.zc.bean.Weibo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

/**
 * Created by 张镇强 on 2016/9/18 15:08.
 */
public class WeiboDaoTest extends BaseTest {
    @Autowired
    WeiboDao dao;
    private Integer initId;

    @Before
    public void setUp() {
        Weibo entity = new Weibo();
        entity.setTopicId(1);
        entity.setWeiboContent("微博内容");
        entity.setReadNum("100亿");

        initId = dao.add(entity);
        Assert.assertTrue(initId == 1);
        initId = entity.getId();
    }

    @After
    public void tearDown() {
//        Assert.assertTrue(dao.del(initId) == 1);
    }

    @Test
    public void getTest() {
        Weibo weibo = dao.get(initId);
        Assert.assertNotNull(weibo);
    }

    @Test
    public void addTest() {
        Weibo entity = new Weibo();
        entity.setTopicId(1);
        entity.setWeiboContent("微博内容");
        entity.setReadNum("100亿");

        int addRes = dao.add(entity);
        Assert.assertTrue(addRes == 1);
    }

    @Test
    public void updateTest() {
        Weibo entity = new Weibo();
        entity.setId(initId);
        entity.setTopicId(1);
        entity.setReadNum("100亿");

        int updateRes = dao.update(entity);
        Weibo selectObj = dao.get(initId);
        Assert.assertTrue(updateRes == 1);
        Assert.assertEquals(Optional.ofNullable(200000l), Optional.ofNullable(selectObj.getReadNum()));
    }

    @Test
    public void delTest() {
        int delRes = dao.del(initId);
        Assert.assertTrue(delRes == 1);
    }
}
