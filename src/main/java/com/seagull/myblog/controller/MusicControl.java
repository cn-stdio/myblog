package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2019/1/18 10:43
 * Description: 音乐页面跳转操作
 */

@Controller
public class MusicControl {

    @GetMapping("/music")
    public String toMusic() {
        return "music";
    }
}
