package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.service.ArchiveService;
import com.seagull.myblog.utils.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Seagull_gby
 * @date 2018/12/20 18:38
 * Description: 归档操作实现
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获得归档文章信息
     * @param rows 每页显示行数
     * @param pageNum 当前页数
     * @return 对应JSON格式
     */
    @Override
    public JSONObject getArchiveArticles(int rows, int pageNum) {
        JSONObject returnArchive = new JSONObject();

        returnArchive.put("code", 200);
        returnArchive.put("msg", "success");

        PageHelper.startPage(pageNum, rows);
        List<Article> articles = articleMapper.queryAllArticles();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        JSONArray datas = new JSONArray();
        articles.forEach(article -> {
            JSONObject data = new JSONObject();

            data.put("articleId", article.getArticleId());
            data.put("title", article.getTitle());
            data.put("createTime", article.getCreateTime().getTime());
            data.put("type", article.getType());
            data.put("attributeLabel", article.getAttributeLabel());

            datas.add(data);
        });

        returnArchive.put("data", datas);

        return returnArchive;
    }

    /**
     * 获得所有文章的日期
     * @return 对应JSON
     */
    @Override
    public JSONObject getArchiveDates() {
        JSONObject returnArchive = new JSONObject();
        TimeUtil timeUtil = new TimeUtil();

        returnArchive.put("code", 200);
        returnArchive.put("msg", "success");

        /* 用有序且不重复的 TreeSet 来保存时间并返回 */
        List<Date> dates = articleMapper.queryAllCreateTime();
        Set<String> times = new TreeSet<>();
        dates.forEach(date -> {
            try {
                times.add(timeUtil.getFormatDateOfym(date));
            } catch (ParseException e) {
                System.out.println("时间转换出错");
                e.printStackTrace();
            }
        });

        JSONArray datas = new JSONArray();
        times.forEach(time -> datas.add(time));

        returnArchive.put("data", datas);

        return returnArchive;
    }
}
