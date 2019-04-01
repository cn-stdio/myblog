package com.seagull.myblog;

import com.aliyun.oss.OSSClient;
import com.seagull.myblog.component.RandomNum;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.mapper.RoleMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.Article;
import com.seagull.myblog.model.User;
import com.seagull.myblog.service.redis.RedisService;
import com.seagull.myblog.utils.AliyunClientUtil;
import net.sf.json.JSONObject;
import org.apache.ibatis.transaction.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RandomNum randomNum;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

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

    @Test
    public void testRandomNum() {
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i=0; i<100; i++) {
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Test
    public void testSmsJSON() {
        JSONObject smsJSON = new JSONObject();
        smsJSON.put("code", "123456");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(String.valueOf(smsJSON));
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Test
    public void testUpload() {
        OSSClient ossClient = AliyunClientUtil.getOSSClient();
/*
        AliyunClientUtil.uploadObjectOSS(ossClient, new File("D:\\bk-9.jpg"), "ectocyst", "boardroom/");
*/
        ossClient.deleteObject("ectocyst", "boardroom/bk-9.jpg");
    }

    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisService.set("redis_string_test", "yayaya");
    }

    /**
     * 获取字符串
     */
    @Test
    public void getString() {
/*
        redisService.setAndTimeOut("redis", "yayaya", 20);
*/
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(redisService.hasKey("redis"));
        System.out.println();
        System.out.println();
    }

    @Test
    public void testGetRole() throws Exception {
        User user = new User();
        user.setId("999A");
        user.setPassword("123456");
        user.setGender(1);
        /*user.setPhone("17888888888");*/
        user.setUserName("ahaha");


        userMapper.insertUser(user);
        roleMapper.insertUserRole(user.getId(), 2);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
