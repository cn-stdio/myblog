package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2018/12/20 18:36
 * Description: 归档操作接口
 */
@Service
public interface ArchiveService {

    /**
     * 获得归档文章信息
     * @param rows 每页显示行数
     * @param pageNum 当前页数
     * @return 对应JSON格式
     */
    public JSONObject getArchiveArticles(int rows, int pageNum);

    /**
     * 获得所有文章的日期
     * @return 对应JSON
     */
    public JSONObject getArchiveDates();
}
