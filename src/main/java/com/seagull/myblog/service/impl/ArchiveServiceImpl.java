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

    private long articleSize;

    /**
     * 对归档JSON中data的添加
     * @param articles 文章列表
     * @return 目标JSON数组
     */
    public JSONArray getArchiveDataJSON(List<Article> articles) {
        JSONArray datas = new JSONArray();

        articles.forEach(article -> {
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

            datas.add(data);
        });

        return datas;
    }

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

        articleSize = pageInfo.getTotal();

        returnArchive.put("pages", pageInfo.getPages());
        returnArchive.put("articleSize", articleSize);
        returnArchive.put("data", getArchiveDataJSON(articles));

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

    /**
     * 获得特定日期的归档文章
     * @param rows 每页显示行数
     * @param pageNum 当前页数
     * @param date 特定日期
     * @return 对应JSON格式
     */
    @Override
    public JSONObject getArchiveArticleOfDate(int rows, int pageNum, String date) throws ParseException {
        String year = date.substring(0, 4);

        /* 对日期界限处理 */
        char endMonthFirst = date.charAt(date.indexOf("月")-2);
        char endMonthSecond = date.charAt(date.indexOf("月")-1);
        String startMonth = String.valueOf(endMonthFirst) + String.valueOf(endMonthSecond);
        String startYear = year + "-";
        String endYear = year + "-";
        String endMonth = String.valueOf(endMonthFirst) + String.valueOf(endMonthSecond);
        if(endMonth.equals("12")) {
            endMonth = "01";
            int endYearInt = Integer.parseInt(year) + 1;
            endYear = endYearInt + "-";
        } else if (endMonth.equals("09")) {
            endMonth = "10";
        } else {
            endMonthSecond += 1;
            endMonth = String.valueOf(endMonthFirst) + String.valueOf(endMonthSecond);
        }

        String startDate = startYear + startMonth +"-01 00:00:00";
        String endDate = endYear + endMonth + "-01 00:00:00";

        JSONObject returnArchive = new JSONObject();

        returnArchive.put("code", 200);
        returnArchive.put("msg", "success");

        PageHelper.startPage(pageNum, rows);
        List<Article> articles = articleMapper.queryArticlesOfDate(startDate, endDate);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);

        returnArchive.put("pages", pageInfo.getPages());
        returnArchive.put("articleSize", articleSize);
        returnArchive.put("data", getArchiveDataJSON(articles));

        return returnArchive;
    }
}
