package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.model.Attribute;
import com.seagull.myblog.service.AdminService;
import com.seagull.myblog.utils.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/16 15:06
 * Description: 管理端实现
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public JSONObject getAllArticle(String userId, int pageNum, int rows) {
        JSONObject allArticle = new JSONObject();
        allArticle.put("code", 200);

        PageHelper.startPage(pageNum, rows);
        List<Article> articles = new ArrayList<>();
        articles = articleMapper.queryAllArticlesByUserId(userId);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        if(articles.isEmpty()) {
            allArticle.put("msg", "noArticle");
            return allArticle;
        }

        TimeUtil timeUtil = new TimeUtil();
        JSONArray data = new JSONArray();
        articles.forEach(article -> {
            Attribute attribute = article.getAttribute();

            JSONObject dataUnit = new JSONObject();
            dataUnit.put("title", article.getTitle());
            dataUnit.put("articleId", article.getArticleId());
            dataUnit.put("summary", article.getSummary());
            try {
                dataUnit.put("time", timeUtil.getFormatDateOfyMdHm(article.getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dataUnit.put("read", attribute.getRead());
            dataUnit.put("comment", attribute.getComment());

            data.add(dataUnit);
        });

        allArticle.put("msg", "success");
        allArticle.put("data", data);
        allArticle.put("articleNum", pageInfo.getTotal());
        return allArticle;
    }
}
