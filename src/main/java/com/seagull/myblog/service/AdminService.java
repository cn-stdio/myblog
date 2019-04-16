package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/16 15:04
 * Description: 管理端接口
 */

@Service
public interface AdminService {

    /**
     * 获取用户全部文章列表
     * @param userId 用户ID
     * @param pageNum  当前页数
     * @param rows  一页大小
     * @return JSON
     */
    public JSONObject getAllArticle(String userId, int pageNum, int rows);
}
