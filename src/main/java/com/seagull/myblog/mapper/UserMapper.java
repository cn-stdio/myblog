package com.seagull.myblog.mapper;

import com.seagull.myblog.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/31 14:28
 * Description: 用户数据库表操作
 */

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询用户（连带权限信息）
     * @param phone 用户手机号（用户名）
     * @return 单个用户实体
     */
    @Results(
            id = "user", value = {
            @Result(property = "role", column = "id", one = @One(select = "com.seagull.myblog.mapper.RoleMapper.queryUserRole")),
            @Result(property = "id", column = "id")
    }
    )
    @Select("SELECT * FROM user WHERE phone LIKE #{phone}")
    public User queryUserByPhone(String phone);

    /**
     * 插入用户
     * @param user 用户
     * @return 插入条数
     */
    @Insert("INSERT INTO user(id, gender, user_name, phone, password) VALUES(#{id}, #{gender}, #{userName}, #{phone}, #{password})")
    public int insertUser(User user);

    /**
     * 获得用户表所有的名字
     * @return 昵称集合
     */
    @Select("SELECT user_name FROM user")
    public List<String> queryAllName();

    /**
     * 获得用户表所有的手机号
     * @return 手机号集合
     */
    @Select("SELECT phone FROM user")
    public List<String> queryAllPhone();

    /**
     * 根据 id 查询某用户昵称
     * @param userId 用户id
     * @return 昵称
     */
    @Select("SELECT user_name FROM user WHERE id LIKE #{userId}")
    public String queryNameById(String userId);

}
