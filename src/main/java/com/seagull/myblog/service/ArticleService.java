package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:29
 * Description: 文章操作接口
 */
@Service
public interface ArticleService {

    /**
     * 获取数据库全部文章
     * @param rows  一页大小
     * @param pageNum  当前页数
     * @return 对应JSON格式数据
     */
    JSONObject getPageArticles(int rows, int pageNum);

    /**
     * 获得指定文章的内容
     * @param articleId 文章Id
     * @return 对应JSON格式数据
     */
    JSONObject getArticlesContent(long articleId);

    /**
     * 文章喜欢数增加
     * @param articleId 文章ID
     * @return JSON
     */
    JSONObject updateArticleLike(long articleId);
}
