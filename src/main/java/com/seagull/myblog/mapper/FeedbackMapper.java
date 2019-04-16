package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询所有反馈记录
     * @return 反馈记录集合
     */
    @Select("SELECT * FROM feedback ORDER BY id DESC")
    public List<Feedback> queryAllFeedback();

    /**
     * 查询未读反馈数目
     * @return 未读反馈数目
     */
    @Select("SELECT COUNT(*) FROM feedback WHERE state = 0")
    public int queryUnreadFeedback();

    /**
     * 更新某条反馈记录为已读
     * @param id 记录标识ID
     * @return 更新条数
     */
    @Update("UPDATE feedback SET state = 1 WHERE id = #{id}")
    public int updateStateById(int id);
}
