package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.service.ArticleService;
import net.sf.json.JSONArray;
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

    @Autowired
    private ArticleMapper articleMapper;

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
    @GetMapping("/updateArticle/{articleId}")
    public String updateArticle() {
        return "updateArticle";
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/updateArticleContent")
    public JSONObject updateArticleContent(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject uac = new JSONObject();
        uac.put("code", 200);

        if(principal==null) {
            uac.put("msg", "noLogin");
        } else {
            Article article = new Article();
            article.setArticleId(Long.valueOf(request.getParameter("articleId")));
            article.setTitle(request.getParameter("title"));
            article.setAuthor(principal.getName());
            article.setContent(request.getParameter("content"));
            article.setAttributeLabel(request.getParameter("attributeLabel"));
            article.setType(request.getParameter("type"));
            article.setClassify(request.getParameter("classify"));

            articleService.updateArticle(article, request.getParameter("htmlContent"));
            uac.put("msg", "success");
        }

        return uac;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/getArticleEditContent")
    public JSONObject updateArticle(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject ua = new JSONObject();
        ua.put("code", 200);

        if(principal==null) {
            ua.put("msg", "noLogin");
        } else {
            long articleId = Long.valueOf(request.getParameter("articleId"));
            Article article = articleMapper.queryArticleByArticleId(articleId);
            ua.put("title", article.getTitle());
            ua.put("content", article.getContent());

            JSONArray tags = new JSONArray();
            String[] tag = article.getAttributeLabel().split(",");
            for (String t : tag) {
                tags.add(t);
            }
            ua.put("tags", tags);
            ua.put("classify", article.getClassify());
            ua.put("type", article.getType());

            ua.put("msg", "success");
        }

        return ua;
    }
}
