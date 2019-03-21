package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/3/20 14:48
 * Description: 标签操作接口
 */

@Service
public interface TagService {

    /**
     * 得到标签目标文章并返回
     * @param rows 每页行数
     * @param pageNum 页数
     * @param tag 标签
     * @return JSON
     */
    public JSONObject getArticleByTag(int rows, int pageNum, String tag);

    /**
     * 得到类别目标文章并返回
     * @param rows 每页行数
     * @param pageNum 页数
     * @param type 标签
     * @return JSON
     */
    public JSONObject getArticleByType(int rows, int pageNum, String type);

    /**
     * 获得所有文章类型
     * @return JSON
     */
    public JSONObject getAllArticleType();
}
