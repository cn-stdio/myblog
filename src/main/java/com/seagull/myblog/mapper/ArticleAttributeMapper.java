package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Attribute;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:30
 * Description: 文章属性数据库操作
 */
@Mapper
@Repository
public interface ArticleAttributeMapper {

    /**
     * 按ID查找所有属性
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM attribute_article WHERE article_id = #{articleId}")
    public Attribute queryAllAttributeArticle(long articleId);

    /**
     * 文章喜欢数+1
     * @param articleId 文章ID
     * @return 更新个数
     */
    @Update("UPDATE attribute_article SET `like` = `like` + 1 WHERE article_id = #{articleId}")
    public int updateArticleLikeById(long articleId);

    /**
     * 文章阅读数+1
     * @param articleId 文章ID
     * @return 更新个数
     */
    @Update("UPDATE attribute_article SET `read` = `read` + 1 WHERE article_id = #{articleId}")
    public int updateArticleReadById(long articleId);

    /**
     * 文章评论数+1
     * @param articleId 文章Id
     * @return 更新个数
     */
    @Update("UPDATE attribute_article SET `comment` = `comment` + 1 WHERE article_id = #{articleId}")
    public int updateArticleCommentById(long articleId);

    /**
     * 插入文章属性
     * @param attribute
     * @return 插入几条数据
     */
    @Insert("INSERT INTO attribute_article(article_id) VALUES(#{articleId})")
    public int insertArticleAttribute(Attribute attribute);
}
