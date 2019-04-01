package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:21
 * Description: 登录跳转
 */

@Controller
public class LoginControl {

    @GetMapping("/login")
    public String loginJump(HttpServletRequest request) {

        request.getSession().setAttribute("url", request.getHeader("Referer"));

        return "login";
    }

}
