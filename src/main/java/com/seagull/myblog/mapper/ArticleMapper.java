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
     * 按照特定日期区间查询文章
     * @param startDate 起始日期
     * @param endDate 终止日期
     * @return
     */
    @ResultMap("article")
    @Select("SELECT * FROM article WHERE create_time >= #{startDate} AND create_time < #{endDate} ORDER BY create_time DESC")
    public List<Article> queryArticlesOfDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询所有文章的创建时间
     * @return
     */
    @Select("SELECT create_time FROM article ORDER BY create_time DESC")
    public List<Date> queryAllCreateTime();

    /**
     * 查询最后文章的创建时间
     * @return
     */
    @Select("SELECT MAX(create_time) FROM article")
    public Date queryLastCreateTime();

    /**
     * 按照文章id查询指定文章
     * @param articleId 文章Id
     * @return
     */
    @ResultMap("article")
    @Select("SELECT * FROM article WHERE article_id = #{articleId}")
    public Article queryArticleByArticleId(long articleId);

    /**
     * 查询包含特定标签的文章
     * @param label 标签
     * @return 文章列表
     */
    @ResultMap("article")
    @Select("SELECT * FROM article WHERE POSITION(REPLACE(#{label},' ','') IN REPLACE(`attribute_label`,' ','')) ORDER BY create_time DESC")
    public List<Article> queryArticlesOfLabel(String label);

    /**
     * 查询特定类别的文章
     * @param type 类别
     * @return 文章列表
     */
    @ResultMap("article")
    @Select("SELECT * FROM article WHERE `type` LIKE #{type} ORDER BY create_time DESC")
    public List<Article> queryArticlesOfType(String type);

    /**
     * 插入文章
     * @param article
     * @return 返回插入文章数量
     */
    @Insert("INSERT INTO article(article_id, title, author, content, attribute_label, summary, type, classify) VALUES(#{articleId}, #{title}, #{author}, #{content}, #{attributeLabel}, #{summary}, #{type}, #{classify})")
    public int insertArticle(Article article);
}
