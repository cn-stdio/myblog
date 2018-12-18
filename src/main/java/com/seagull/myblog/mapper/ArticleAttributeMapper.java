package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Attribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
