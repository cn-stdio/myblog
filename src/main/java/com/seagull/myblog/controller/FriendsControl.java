package com.seagull.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Seagull_gby
 * @date 2019/3/14 11:28
 * Description: 友链跳转类
 */

@Controller
public class FriendsControl {

    @GetMapping("/friends")
    public String toFriends() {
        return "friends";
    }
}
