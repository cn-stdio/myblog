package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.component.RandomNum;
import com.seagull.myblog.mapper.ArticleAttributeMapper;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.model.Attribute;
import com.seagull.myblog.service.ArticleService;
import com.seagull.myblog.service.redis.RedisService;
import com.seagull.myblog.utils.InterceptionUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:31
 * Description: 文章操作实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleAttributeMapper articleAttributeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    /**
     * 获取数据库全部文章
     * @param rows  一页大小
     * @param pageNum  当前页数
     * @return 对应JSON格式数据
     */
    @Override
    public JSONObject getPageArticles(int rows, int pageNum) {
        JSONObject returnArticles = new JSONObject();

        PageHelper.startPage(pageNum, rows);
        List<Article> articles = articleMapper.queryAllArticles();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        returnArticles.put("code", 200);
        returnArticles.put("msg", "success");
        returnArticles.put("pages", pageInfo.getPages());

        JSONObject data = new JSONObject();
        JSONArray datas = new JSONArray();
        articles.forEach(article -> {
            JSONArray returnJsonArray = new JSONArray();
            List<String> labels = new ArrayList<>();
            data.put("articleId", article.getArticleId());
            data.put("title", article.getTitle());
            data.put("createTime", article.getCreateTime().getTime());
            data.put("classify", article.getClassify());
            data.put("type", article.getType());
            data.put("summary", article.getSummary());
            data.put("read", article.getAttribute().getRead());
            labels = Arrays.asList(article.getAttributeLabel().split(","));
            returnJsonArray = JSONArray.fromObject(labels);
            data.put("attributeLabel", returnJsonArray.toString());
            data.put("attributeLabelCount", labels.size());
            datas.add(data);
        });

        returnArticles.put("data", datas);

        return returnArticles;
    }

    /**
     * 获得指定文章的内容
     * @param articleId 文章Id
     * @return 对应JSON格式数据
     */
    @Override
    public JSONObject getArticlesContent(long articleId) {
        JSONObject returnArticleContent = new JSONObject();
        JSONArray returnJsonArray = new JSONArray();
        List<String> labels = new ArrayList<>();

        returnArticleContent.put("code", 200);
        returnArticleContent.put("msg", "success");

        List<Article> articles = articleMapper.queryAllArticles();
        Article article = articleMapper.queryArticleByArticleId(articleId);

        JSONObject data = new JSONObject();
        data.put("title", article.getTitle());
        data.put("content", article.getContent());
        data.put("summary", article.getSummary());
        data.put("classify", article.getClassify());
        data.put("type", article.getType());
        data.put("createTime", article.getCreateTime().getTime());

        labels = Arrays.asList(article.getAttributeLabel().split(","));
        returnJsonArray = JSONArray.fromObject(labels);
        data.put("attributeLabel", returnJsonArray.toString());
        data.put("attributeLabelCount", labels.size());
        data.put("read", article.getAttribute().getRead());
        data.put("like", article.getAttribute().getLike());

        for(int i=0; i<articles.size(); i++) {
            /* 因为倒序返回文章列表，所以prev与next颠倒 */
            if(articles.get(i).getArticleId() == articleId) {
                /* 访问的文章是最新发表的文章 */
                if(i==0) {
                    data.put("nextArticleId", "no");
                    data.put("nextArticleTitle", "no");

                    if(articles.size()==1) {
                        data.put("prevArticleId", "no");
                        data.put("prevArticleTitle", "no");
                    } else {
                        data.put("prevArticleId", articles.get(i+1).getArticleId());
                        data.put("prevArticleTitle", articles.get(i+1).getTitle());
                    }

                    break;
                }

                /* 访问的文章是第一篇文章 */
                if(i==articles.size()-1) {
                    data.put("prevArticleId", "no");
                    data.put("prevArticleTitle", "no");

                    if(articles.size()==1) {
                        data.put("nextArticleId", "no");
                        data.put("nextArticleTitle", "no");
                    } else {
                        data.put("nextArticleId", articles.get(i-1).getArticleId());
                        data.put("nextArticleTitle", articles.get(i-1).getTitle());
                    }

                    break;
                }

                data.put("prevArticleId", articles.get(i+1).getArticleId());
                data.put("prevArticleTitle", articles.get(i+1).getTitle());
                data.put("nextArticleId", articles.get(i-1).getArticleId());
                data.put("nextArticleTitle", articles.get(i-1).getTitle());
                break;
            }
        }

        returnArticleContent.put("data", data);

        return returnArticleContent;
    }

    /**
     * 文章喜欢数增加
     * @param articleId 文章ID
     * @return JSON
     */
    @Override
    public JSONObject updateArticleLike(long articleId, String userId) {
        JSONObject likeArticle = new JSONObject();
        likeArticle.put("code", 200);

        int count = userMapper.queryUserLikeOfArticle(userId, articleId);
        if(count == 0) {
            userMapper.insertUserLikeOfArticle(userId, articleId);
        } else {
            likeArticle.put("msg", "您已经点过赞了哟~");

            return likeArticle;
        }

        articleAttributeMapper.updateArticleLikeById(articleId);

        likeArticle.put("msg", "success");
        return likeArticle;
    }

    @Override
    public JSONObject updateArticleRead(String ip, long articleId) {
        JSONObject readArticle = new JSONObject();
        long nowTime = System.currentTimeMillis();
        readArticle.put("code", 200);

        Set articles = redisService.members(ip);
        AtomicInteger count = new AtomicInteger(0);
        if(!articles.isEmpty()) {
            articles.forEach(article -> {
                if(Long.valueOf(String.valueOf(article)) == articleId) {
                    count.getAndIncrement();
                    return;
                }
            });
        } else {
            redisService.sadd(ip, articleId);
            redisService.expire(ip, 7200);
            articleAttributeMapper.updateArticleReadById(articleId);
        }
        if(count.get() == 0 && !articles.isEmpty()) {
            redisService.sadd(ip, articleId);
            articleAttributeMapper.updateArticleReadById(articleId);
        } else {
            readArticle.put("msg", "您在2小时内已经阅读过这篇文章嘞，直接访问接口可不是个好孩纸~");

            return readArticle;
        }

        readArticle.put("msg", "success");
        return readArticle;
    }

    @Override
    public void insertArticle(Article article, String contentHtml) {
        Attribute attribute = new Attribute();

        // 用当前时间的时间戳设置专属文章ID
        long dateString = System.currentTimeMillis();
        article.setArticleId(dateString);
        attribute.setArticleId(dateString);

        // 对文章摘要的截取，截取内容为80个字符（中文也算1个字符）
        InterceptionUtil interceptionArticleUtil = new InterceptionUtil();
        String summary = interceptionArticleUtil.interceptionArticle(contentHtml);
        article.setSummary(summary);

        articleMapper.insertArticle(article);
        articleAttributeMapper.insertArticleAttribute(attribute);
    }

    @Override
    public void updateArticle(Article article, String contentHtml) {

        // 对文章摘要的截取，截取内容为80个字符（中文也算1个字符）
        InterceptionUtil interceptionArticleUtil = new InterceptionUtil();
        String summary = interceptionArticleUtil.interceptionArticle(contentHtml);
        article.setSummary(summary);

        articleMapper.updateArticle(article);
    }
}
