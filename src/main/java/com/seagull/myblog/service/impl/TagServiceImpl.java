package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.mapper.TypeMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.model.Attribute;
import com.seagull.myblog.service.TagService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/20 14:51
 * Description: 标签操作实现类
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TypeMapper typeMapper;

    private long articleSize;

    private JSONObject dataJSONPut(Article article) {
        JSONObject data = new JSONObject();

        data.put("articleId", article.getArticleId());
        data.put("title", article.getTitle());
        data.put("createTime", article.getCreateTime().getTime());
        data.put("type", article.getType());

        String[] attributes = article.getAttributeLabel().split(",");
        JSONArray ats = new JSONArray();
        for (String att : attributes) {
            ats.add(att);
        }
        data.put("attributeLabel", ats);

        return data;
    }

    @Override
    public JSONObject getArticleByTag(int rows, int pageNum, String tag) {
        JSONObject tagArticle = new JSONObject();
        JSONArray datas = new JSONArray();
        List<Article> articles = new ArrayList<>();

        articles = articleMapper.queryArticlesOfLabel(tag);
        for(Article article : articles) {
            String[] labels = article.getAttributeLabel().split(",");
            Attribute attribute = article.getAttribute();

            /* 去除包含标签label的标签的文章，比如查询标签为spring，则去除标签为spring boot之类的文章 */
            for(String l : labels) {
                if(l.replaceAll(" ", "").equalsIgnoreCase(tag.replaceAll(" ", ""))) {
                    JSONObject data = new JSONObject();
                    data = this.dataJSONPut(article);
                    datas.add(data);
                    break;
                }
            }
        }

        /* 分页处理 */
        int pageFirst = rows * (pageNum-1) + 1;
        JSONArray dataPageDeal = new JSONArray();
        for(int i=pageFirst-1; i<pageFirst-1+rows; i++) {
            if(i>=datas.size()) {
                break;
            }
            dataPageDeal.add(datas.get(i));
        }
        if(dataPageDeal.size()<=0) {
            tagArticle.put("data", "无");
        } else {
            tagArticle.put("data", dataPageDeal);
        }

        /* 分页JSON加入 */
        int totalPage = 0;
        if(datas.size() % rows == 0) {
            totalPage = datas.size()/rows;
        } else {
            totalPage = (datas.size()/rows)+1;
        }

        tagArticle.put("code", 200);
        tagArticle.put("msg", "success");
        tagArticle.put("pages", totalPage);
        tagArticle.put("articleSize", datas.size());

        return tagArticle;
    }

    @Override
    public JSONObject getArticleByType(int rows, int pageNum, String type) {
        JSONObject tagArticle = new JSONObject();
        List<Article> articles = new ArrayList<>();

        PageHelper.startPage(pageNum, rows);
        articles = articleMapper.queryArticlesOfType(type);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(type);
        System.out.println();
        System.out.println();
        System.out.println();

        articleSize = pageInfo.getTotal();

        JSONArray datas = new JSONArray();
        articles.forEach(article -> {
            JSONObject data = new JSONObject();
            data = this.dataJSONPut(article);
            datas.add(data);
        });

        tagArticle.put("code", 200);
        tagArticle.put("msg", "success");
        tagArticle.put("pages", pageInfo.getPages());
        tagArticle.put("articleSize", articleSize);
        tagArticle.put("data", datas);

        return tagArticle;
    }

    @Override
    public JSONObject getAllArticleType() {
        JSONObject articleType = new JSONObject();

        List<String> types = new ArrayList<>();
        types = typeMapper.queryTypeNoRepeat();

        JSONArray data = new JSONArray();
        types.forEach(type -> data.add(type));

        articleType.put("code", 200);
        articleType.put("msg", "success");
        articleType.put("data", data);
        return articleType;
    }
}
