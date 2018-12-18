package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2018/12/17 19:35
 * Description: 主页跳转
 */
@Controller
public class indexControl {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String indexTwo() {
        return "index";
    }
}
