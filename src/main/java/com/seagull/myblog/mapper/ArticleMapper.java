package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:21
 * Description: 文章数据库操作
 */
@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 查询所有文章语句
     * @return
     */
    @Results(
            id = "article", value = {
            @Result(property = "attribute", column = "article_id", many = @Many(select = "com.seagull.myblog.mapper.ArticleAttributeMapper.queryAllAttributeArticle")),
            @Result(property = "articleId", column = "article_id")
    }
    )
    @Select("SELECT * FROM article ORDER BY create_time DESC")
    public List<Article> queryAllArticles();

    /**
     * 查询所有文章的创建时间
     * @return
     */
    @Select("SELECT create_time FROM article ORDER BY create_time DESC")
    public List<Date> queryAllCreateTime();
}
