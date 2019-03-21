package com.seagull.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/20 15:58
 * Description: 类型数据库操作
 */

@Mapper
@Repository
public interface TypeMapper {

    /**
     * 查找所有类别（不重复）
     * @return 类别集合
     */
    @Select("SELECT DISTINCT `type` FROM article")
    public List<String> queryTypeNoRepeat();
}
