package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:21
 * Description: 登录跳转
 */

@Controller
public class LoginControl {

    @GetMapping("/login")
    public String loginJump() {
        return "login";
    }
}
