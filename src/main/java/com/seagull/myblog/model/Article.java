package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:19
 * Description: 文章实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private long id;

    /**
     * 文章ID
     */
    private long articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 文章主要标签
     */
    private String mainLabel;
    /**
     * 文章属性标签
     */
    private String attributeLabel;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章属性
     */
    private Attribute attribute;

    /**
     * 文章类型
     */
    private String type;
}
