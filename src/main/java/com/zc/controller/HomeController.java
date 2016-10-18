package com.zc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张镇强 on 2016/10/18 11:54.
 */
@Controller
public class HomeController {
    @RequestMapping("home")
    public String home() {
        return "redirect:hotspots";
    }
}
