package com.seagull.myblog.mapper;

import com.seagull.myblog.model.ReplyInformation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/13 21:47
 * Description: 消息数据库操作
 */

@Mapper
@Repository
public interface InformationMapper {

    /**
     * 查询某人的所有回复消息
     * @param answerId 被回复者ID
     * @return 消息集合
     */
    @Select("SELECT * FROM reply_information WHERE answer_id = #{answerId} ORDER BY id DESC")
    public List<ReplyInformation> queryAllReplyInformationById(String answerId);

    /**
     * 查询某人的回复消息未读数
     * @param answerId 被回复者ID
     * @return 消息未读数
     */
    @Select("SELECT COUNT(*) FROM reply_information WHERE answer_id = #{answerId} AND state = 0")
    public int queryReplyInformationCount(String answerId);

    /**
     * 将某人某条未读消息更新为已读
     * @param answerId 被回复者ID
     * @param id 回复记录特定标识
     * @return 更新条数
     */
    @Update("UPDATE reply_information SET state = 1 WHERE answer_id = #{answerId} AND id = #{id}")
    public int updateReplyInformationStateById(@Param("answerId") String answerId, @Param("id") int id);

    /**
     * 插入一条回复消息
     * @param replyInformation 回复消息实体类
     * @return 插入数目
     */
    @Insert("INSERT INTO reply_information(article_id, respondent_id, answer_id, content, reply_time) VALUES(#{articleId}, #{respondentId}, #{answerId}, #{content}, #{replyTime})")
    public int insertReplyInformation(ReplyInformation replyInformation);
}
