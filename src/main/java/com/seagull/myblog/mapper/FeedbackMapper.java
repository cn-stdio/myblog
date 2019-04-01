package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Seagull_gby
 * @date 2019/4/1 15:05
 * Description: 反馈接口
 */

@Mapper
@Repository
public interface FeedbackMapper {

    /**
     * 插入一条反馈记录
     * @param feedback 反馈实体（包括信息和联系方式）
     * @return 插入条数
     */
    @Insert("INSERT INTO feedback(msg, contact) VALUES(#{msg}, #{contact})")
    public int insertFeedback(Feedback feedback);
}
