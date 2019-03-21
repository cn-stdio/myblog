package com.seagull.myblog;

import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.utils.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void queryArticlesOfDateTest() throws ParseException {
        String startDate = "2018-09-01 00:00:00";
        String endDate = "2018-10-01 00:00:00";

        List<Article> articles = articleMapper.queryArticlesOfDate(startDate, endDate);

        System.out.println(articles);
    }

    @Test
    public void testTagSearch() {
        List<Article> articles = new ArrayList<>();

        articles = articleMapper.queryArticlesOfType("面经");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(articles);
        System.out.println();
        System.out.println();
        System.out.println();

    }

}
