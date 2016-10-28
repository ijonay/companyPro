/**
 * @title AdminController.java
 * @author xyzhuzhou/zhuzz@heptax.com
 * @date：2016年8月3日 下午6:41:45
 * @Tag 类说明
 * @Copyright 2016 知藏. All right reserved.
 */
package com.zc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {



    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("/redisset")
    public String redisset(ModelMap model) {

        return "admin/redisset";
    }



}
