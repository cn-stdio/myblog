package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2019/3/14 11:30
 * Description: 关于我跳转
 */

@Controller
public class AboutMeControl {

    @GetMapping("/aboutMe")
    public String toAboutMe() {
        return "aboutMe";
    }
}
