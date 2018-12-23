package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2018/12/23 12:41
 * Description: 更新界面跳转
 */
@Controller
public class UpdateControl {

    @GetMapping("/update")
    public String update() {
        return "update";
    }
}
