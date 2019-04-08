package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:20
 * Description: 用户评论点赞记录实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentLikeRecord {

    private long id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 评论所在文章Id
     */
    private long articleId;

    /**
     * 评论层级Id
     */
    private String selfId;
}