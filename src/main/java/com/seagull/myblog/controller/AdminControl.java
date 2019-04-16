package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.FeedbackMapper;
import com.seagull.myblog.service.AdminService;
import com.seagull.myblog.service.FeedbackService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Seagull_gby
 * @date 2019/4/15 20:46
 * Description: 管理端跳转
 */

@Controller
public class AdminControl {

    @Autowired
    private AdminService adminService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/admin/getAllArticle")
    public JSONObject getAllArticle(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject articles = new JSONObject();

        if(principal==null) {
            articles.put("code", 200);
            articles.put("msg", "noLogin");
            return articles;
        } else {
            articles = adminService.getAllArticle(principal.getName(), Integer.valueOf(request.getParameter("pageNum")), Integer.valueOf(request.getParameter("rows")));
        }

        return articles;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/admin/getFeedback")
    public JSONObject getFeedback(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject feedback = new JSONObject();

        if(principal==null) {
            feedback.put("code", 200);
            feedback.put("msg", "noLogin");
            return feedback;
        } else {
            feedback = feedbackService.getFeedback(Integer.valueOf(request.getParameter("pageNum")), Integer.valueOf(request.getParameter("rows")));
        }

        return feedback;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/getUserFeedbackNum")
    public JSONObject getUserFeedbackNum(@AuthenticationPrincipal Principal principal) {
        JSONObject fn = new JSONObject();
        fn.put("code", 200);

        if(principal==null) {
            fn.put("msg", "noLogin");
        } else {
            int feedbackNum = feedbackMapper.queryUnreadFeedback();
            fn.put("msg", "success");
            fn.put("feedbackNum", feedbackNum);
        }

        return fn;
    }
}
