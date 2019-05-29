package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Friend;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/5/26 13:08
 * Description: 友链数据库处理
 */

@Mapper
@Repository
public interface FriendsMapper {

    /**
     * 查询所有友链信息
     * @return 友链集合
     */
    @Select("SELECT * FROM `friend`")
    public List<Friend> queryAllFriends();

    /**
     * 更新指定ID友链信息
     * @param friend 友链实体
     * @return 更新条数
     */
    @Update("UPDATE `friend` SET name = #{name}, introduce = #{introduce}, head_img = #{headImg}, url = #{url} WHERE id = #{id}")
    public int updateFriendById(Friend friend);

    /**
     * 插入一条友链信息
     * @param friend 友链实体
     * @return 插入条数
     */
    @Insert("INSERT INTO `friend`(name, introduce, head_img, url) VALUES(#{name}, #{introduce}, #{headImg}, #{url})")
    public int insertFriend(Friend friend);

    /**
     * 删除指定友链信息
     * @param id 友链uuid
     * @return 删除数目
     */
    @Delete("DELETE FROM `friend` WHERE id = #{id}")
    public int deleteFriendById(String id);
}
