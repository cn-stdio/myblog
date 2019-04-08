package com.seagull.myblog.controller;

import net.sf.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author Seagull_gby
 * @date 2019/4/8 20:29
 * Description: 留言板跳转控制
 */

@Controller
public class MessageBoardControl {

    @GetMapping("/messageBoard")
    public String messageJump() {
        return "messageBoard";
    }

    @ResponseBody
    @GetMapping("/checkLogin")
    public JSONObject checkLogin(@AuthenticationPrincipal Principal principal) {
        JSONObject login = new JSONObject();
        login.put("code", 200);

        if(principal==null) {
            login.put("msg", "noLogin");
        } else {
            login.put("msg", "success");
        }

        return login;
    }
}
