package com.seagull.myblog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2019/4/8 21:38
 * Description: 发表文章跳转控制
 */

@Controller
public class PublishControl {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
