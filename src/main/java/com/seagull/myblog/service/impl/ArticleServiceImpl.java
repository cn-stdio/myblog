package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.service.ArticleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:31
 * Description: 文章操作实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

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
            data.put("title", article.getTitle());
            data.put("createTime", article.getCreateTime().getTime());
            data.put("type", article.getType());
            data.put("summary", article.getSummary());
            data.put("read", article.getAttribute().getRead());
            data.put("mainLabel", article.getMainLabel());
            datas.add(data);
        });

        returnArticles.put("data", datas);

        return returnArticles;
    }
}
