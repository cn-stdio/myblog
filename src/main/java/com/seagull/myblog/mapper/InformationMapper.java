package com.seagull.myblog.mapper;

import com.seagull.myblog.model.ReplyInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("SELECT * FROM reply_infomation WHERE answer_id = #{answerId}")
    public List<ReplyInformation> queryAllReplyInfomation(String answerId);
}
