package com.seagull.myblog.controller;

import com.seagull.myblog.model.Article;
import com.seagull.myblog.service.ArticleService;
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
 * @date 2019/4/8 21:38
 * Description: 发表文章跳转控制
 */

@Controller
public class PublishControl {

    @Autowired
    private ArticleService articleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/publishSuccess")
    public String publishSuccess() {
        return "publishSuccess";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/insertArticle")
    public JSONObject insertArticle(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject ia = new JSONObject();
        ia.put("code", 200);

        if(principal==null) {
            ia.put("msg", "noLogin");
        } else {
            Article article = new Article();
            article.setTitle(request.getParameter("title"));
            article.setAuthor(principal.getName());
            article.setContent(request.getParameter("content"));
            article.setAttributeLabel(request.getParameter("attributeLabel"));
            article.setType(request.getParameter("type"));
            article.setClassify(request.getParameter("classify"));

            articleService.insertArticle(article, request.getParameter("htmlContent"));
            ia.put("msg", "success");
        }

        return ia;
    }
}
