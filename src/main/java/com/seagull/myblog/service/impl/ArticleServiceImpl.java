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

    /**
     * 获得指定文章的内容
     * @param articleId 文章Id
     * @return 对应JSON格式数据
     */
    @Override
    public JSONObject getArticlesContent(long articleId) {
        JSONObject returnArticleContent = new JSONObject();

        returnArticleContent.put("code", 200);
        returnArticleContent.put("msg", "success");

        List<Article> articles = articleMapper.queryAllArticles();
        Article article = articleMapper.queryArticleByArticleId(articleId);

        JSONObject data = new JSONObject();
        data.put("title", article.getTitle());
        data.put("content", article.getContent());
        data.put("type", article.getType());
        data.put("createTime", article.getCreateTime().getTime());
        data.put("mainLabel", article.getMainLabel());
        data.put("read", article.getAttribute().getRead());

        for(int i=0; i<articles.size(); i++) {
            if(articles.get(i).getArticleId() == articleId) {
                if(i==0) {
                    data.put("prevArticleId", "no");
                    data.put("prevArticleTitle", "no");
                    data.put("nextArticleId", articles.get(i+1).getArticleId());
                    data.put("nextArticleTitle", articles.get(i+1).getTitle());
                    break;
                }
                if(i==articles.size()-1) {
                    data.put("nextArticleId", "no");
                    data.put("nextArticleTitle", "no");
                    data.put("prevArticleId", articles.get(i-1).getArticleId());
                    data.put("prevArticleTitle", articles.get(i-1).getTitle());
                    break;
                }

                data.put("prevArticleId", articles.get(i-1).getArticleId());
                data.put("prevArticleTitle", articles.get(i-1).getTitle());
                data.put("nextArticleId", articles.get(i+1).getArticleId());
                data.put("nextArticleTitle", articles.get(i+1).getTitle());
                break;
            }
        }

        returnArticleContent.put("data", data);

        return returnArticleContent;
    }
}
