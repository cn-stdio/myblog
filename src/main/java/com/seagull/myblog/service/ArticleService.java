package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;

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

}
