package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2018/12/16 22:20
 * Description: 文章属性实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

    private long id;

    /**
     * 文章ID
     */
    private long articleId;

    /**
     * 喜欢数量
     */
    private int like;

    /**
     * 评论数量
     */
    private int comment;

    /**
     * 阅读数量
     */
    private int read;
}
