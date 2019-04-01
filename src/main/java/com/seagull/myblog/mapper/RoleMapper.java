package com.seagull.myblog.mapper;

import com.seagull.myblog.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Seagull_gby
 * @date 2019/3/31 14:04
 * Description: 权限数据库操作
 */

@Mapper
@Repository
public interface RoleMapper {

    /**
     * 用嵌套查询某用户权限
     * @param userId 用户ID
     * @return 权限信息
     */
    @Select("SELECT * FROM role WHERE id = (SELECT role_id FROM user_role WHERE user_id LIKE #{userId})")
    public Role queryUserRole(String userId);

    /**
     * 插入用户权限
     * @param userId 用户ID
     * @param roleId 权限ID
     * @return 插入条数
     */
    @Insert("INSERT INTO user_role(user_id, role_id) VALUES(#{userId}, #{roleId})")
    public int insertUserRole(@Param("userId") String userId, @Param("roleId")int roleId);
}
