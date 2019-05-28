package com.seagull.myblog.controller;

import com.seagull.myblog.service.FeedbackService;
import com.seagull.myblog.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Seagull_gby
 * @date 2018/12/17 19:35
 * Description: 主页跳转
 */
@Controller
public class IndexControl {

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String indexTwo() {
        return "index";
    }

    @GetMapping("/403")
    public String errorAuthority() {
        return "error/403";
    }

    /**
     * 获得用户昵称
     * @param principal 用于获得登录后的用户ID
     * @return JSON
     */
    @ResponseBody
    @GetMapping("/getUserName")
    public JSONObject getUserName(@AuthenticationPrincipal Principal principal) {
        JSONObject username = new JSONObject();
        username.put("code", 200);

        if (principal == null) {
            username.put("msg", "false");
        } else {
            username = userService.getUserName(principal.getName());
        }

        return username;
    }

    /**
     * 插入反馈记录
     * @param request 请求域
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/insertFeedback")
    public JSONObject insertFeedback(HttpServletRequest request) {
        JSONObject ifb = new JSONObject();

        ifb = feedbackService.insertFeedback(request.getParameter("msg"), request.getParameter("contact"));

        return ifb;
    }
}
