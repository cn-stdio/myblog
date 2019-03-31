package com.seagull.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/28 15:13
 * Description: 注册数据库操作类
 */

@Mapper
@Repository
public interface RegisterMapper {

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
}
