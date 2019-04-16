package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Comment;
import com.seagull.myblog.model.UserCommentLikeRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:18
 * Description: 文章评论功能数据库实现语句
 */
@Mapper
@Repository
public interface ArticleCommentMapper {

    /**
     * 插入文章评论
     * @param comment
     * @return 成功插入数量
     */
    @Insert("INSERT INTO reviews_article(self_id, article_id, original_author, answer_name, respondent_name, comment_content) VALUES(#{selfId}, #{articleId}, #{originalAuthor}, #{answerName}, #{respondentName}, #{commentContent})")
    public int insertArticleCommen(Comment comment);

    /**
     * 根据文章ID查询该文章中楼主的评论
     * @param articleId 目标文章Id
     * @return
     */
    @Select("SELECT * FROM reviews_article WHERE article_id = #{articleId} AND (length(self_id)-length(replace(self_id, ',' , ''))) = 0 ORDER BY CONVERT(self_id, SIGNED) DESC")
    public List<Comment> queryFloorCommentsById(long articleId);

    /**
     * 根据文章ID查询该文章的所有评论
     * @param articleId 目标文章Id
     * @return
     */
    @Select("SELECT * FROM reviews_article WHERE article_id = #{articleId} ORDER BY id ASC")
    public List<Comment> queryCommentsByArticleId(long articleId);

    /**
     * 更新评论点赞数使其+1
     * @param articleId 评论所在文章Id
     * @param selfId 评论层级Id
     * @return 更新数目
     */
    @Update("UPDATE reviews_article SET `likes` = `likes`+1 WHERE article_id = #{articleId} AND self_id LIKE #{selfId}")
    public int updateCommentLikePlus(@Param("articleId") long articleId, @Param("selfId") String selfId);

    /**
     * 更新评论点赞数使其-1
     * @param articleId 评论所在文章Id
     * @param selfId 评论层级Id
     * @return 更新数目
     */
    @Update("UPDATE reviews_article SET `likes` = `likes`-1 WHERE article_id = #{articleId} AND self_id LIKE #{selfId}")
    public int updateCommentLikeMinus(@Param("articleId") long articleId, @Param("selfId") String selfId);

    /**
     * 插入用户与对应评论点赞关系
     * @param userId 用户Id
     * @param articleId 评论所在文章Id
     * @param selfId 评论层级Id
     * @return 插入数目
     */
    @Insert("INSERT INTO user_comment_like(user_id, article_id, self_id) VALUES(#{userId}, #{articleId}, #{selfId})")
    public int insertUserCommentLikeRecord(@Param("userId") String userId, @Param("articleId") long articleId, @Param("selfId") String selfId);

    /**
     * 查找该用户点赞过的目标文章下的所有评论
     * @param userId 用户Id
     * @param articleId 文章Id
     * @return 对应所有评论
     */
    @Select("SELECT * FROM user_comment_like WHERE user_id LIKE #{userId} AND article_id = #{articleId}")
    public List<UserCommentLikeRecord> queryAllUserCommentLikeRecordByUserId(@Param("userId")String userId, @Param("articleId")long articleId);

    /**
     * 查找该用户是否点赞过某文章的某评论
     * @param userId 用户Id
     * @param articleId 文章Id
     * @param selfId 层级ID
     * @return 数目
     */
    @Select("SELECT COUNT(*) FROM user_comment_like WHERE user_id LIKE #{userId} AND article_id = #{articleId} AND self_id LIKE #{selfId}")
    public int queryCommentLikeCountById(@Param("userId")String userId, @Param("articleId")long articleId, @Param("selfId") String selfId);


    /**
     * 删除目标用户对目标评论点赞记录
     * @param userId 用户Id
     * @param articleId 评论所在文章Id
     * @param selfId 层级Id
     * @return 删除条数
     */
    @Delete("DELETE FROM user_comment_like WHERE user_id = #{userId} AND article_id = #{articleId} AND self_id LIKE #{selfId}")
    public int deleteUserCommentLikeRecord(@Param("userId") String userId, @Param("articleId") long articleId, @Param("selfId") String selfId);

    /**
     * 查询某文章下某个特定评论
     * @param articleId 文章ID
     * @param selfId 评论层级
     * @return 评论
     */
    @Select("SELECT * FROM reviews_article WHERE article_id = #{articleId} AND self_id LIKE #{selfId}")
    public Comment queryDateById(@Param("articleId") long articleId, @Param("selfId") String selfId);
}
