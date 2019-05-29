package com.zc.controller;

import com.zc.bean.Users;
import com.zc.service.UsersService;
import org.apache.log4j.Logger;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by xyzhuzhou on 2016/10/27 0027 11:20:56.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    private UsersService userService = null;

    /**
     * 注册用户
     *
     * @param @param  username
     * @param @param  password
     * @param @param  model
     * @param @return
     * @return String
     * @date 2016年10月27日11:21:57
     */
    @RequestMapping("/regist")
    public String registUserInfo(String username, String password, Model model) {
        logger.info("regist : start , username : " + username + " password : "
                + password);
        logger.info("regist : start , username : " + username + " password : "
                + password);
        String view = "welcome";

        if (StringUtils.isEmpty(username)) {
            model.addAttribute("registInfo", "用户名不能为空！");
            return view;
        }

        if (StringUtils.isEmpty(password)) {
            model.addAttribute("registInfo", "密码不能为空！");
            return view;
        }
        // 用户信息加密处理
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();

        String md5code = new SimpleHash("MD5", password, username + salt, 5)
                .toString();

        Users users = new Users();
        users.setUserName(username);
        users.setPassword(md5code);
        users.setSalt(salt);
        try {
            userService.add(users);
        } catch (Exception e) {

            e.printStackTrace();
            model.addAttribute("registInfo", "注册失败 : " + e.getMessage());
            return "welcome";
        }

        model.addAttribute("registInfo", "注册成功");
        return "welcome";
    }


}
