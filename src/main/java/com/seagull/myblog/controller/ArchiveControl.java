package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2018/12/19 21:01
 * Description: 归档跳转
 */
@Controller
public class ArchiveControl {

    @GetMapping("/archive")
    public String archive() {
        return "archive";
    }
}
