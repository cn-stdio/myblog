package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.service.ArticleService;
import com.seagull.myblog.service.redis.RedisService;
import com.seagull.myblog.utils.IpUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:26
 * Description: 文章跳转类
 */
@RestController
public class ArticleControl {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 获得所有文章
     * @param request 请求域
     * @return JSON
     */
    @RequestMapping("/getAllArticles")
    public JSONObject queryArticles(HttpServletRequest request) {
        JSONObject returnArticles = new JSONObject();

        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        returnArticles = articleService.getPageArticles(rows, pageNum);

        return returnArticles;
    }

    /**
     * 检查目标用户是否已经对某文章点赞
     * @param principal 获取用户
     * @param request 请求域
     * @return JSON
     */
    @GetMapping("/checkArticleLike")
    public JSONObject checkArticleLike(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject cls = new JSONObject();
        cls.put("code", 200);

        if(principal==null) {
            cls.put("msg", "noLogin");
            return cls;
        } else {
            int count = userMapper.queryUserLikeOfArticle(principal.getName(), Long.valueOf(request.getParameter("articleId")));
            if(count==0) {
                cls.put("msg", "nonExist");
                return cls;
            } else {
                cls.put("msg", "exist");
                return cls;
            }
        }
    }

    /**
     * 检查某IP是否已阅读过某篇文章（redis过期时间2小时）
     * @param request 请求域
     * @return JSON
     */
    @GetMapping("/checkArticleRead")
    public JSONObject checkArticleRead(HttpServletRequest request) {
        JSONObject cr = new JSONObject();
        cr.put("code", 200);

        String ip = IpUtil.getIpAddr(request);
        Set articles = redisService.members(ip);
        AtomicInteger count = new AtomicInteger(0);
        if(articles.isEmpty()) {
            cr.put("msg", "nonExist");
            cr.put("ip", ip);
            return cr;
        } else {
            articles.forEach(article -> {
                if(String.valueOf(article).equals(request.getParameter("articleId"))) {
                    cr.put("msg", "exist");
                    count.getAndIncrement();
                    return;
                }
            });
        }
        if(count.get()==0) {
            cr.put("msg", "nonExist");
        }

        cr.put("ip", ip);
        return cr;
    }

}
