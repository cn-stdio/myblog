package com.seagull.myblog.controller;

import com.seagull.myblog.service.ArticleService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:26
 * Description: 文章跳转类
 */
@RestController
public class ArticleControl {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getAllArticles")
    public JSONObject queryArticles(HttpServletRequest request) {
        JSONObject returnArticles = new JSONObject();

        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        returnArticles = articleService.getPageArticles(rows, pageNum);

        return returnArticles;
    }
}
