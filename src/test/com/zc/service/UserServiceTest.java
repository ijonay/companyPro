package com.zc.service;

import com.zc.BaseTest;
import com.zc.bean.Users;
import com.zc.model.usermodel.LoginStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 张镇强 on 2016/10/14 18:29.
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void addUserTest() {
//        Users users = new Users();
//        users.setUserName("heptax");
//        users.setPassword("Zhicang@2016");
//        Assert.assertTrue(usersService.add(users));
    }

    @Test
    public void loginTest() {
//        LoginStatus loginStatus = usersService.login("heptax", "Zhicang@2016");
//        Assert.assertTrue(loginStatus.isLoggedIn());
    }
}
