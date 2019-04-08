package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:19
 * Description: 评论实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private long id;

    /**
     * 评论本身id
     */
    private String selfId;

    /**
     * 文章id
     */
    private long articleId;

    /**
     * 文章作者
     */
    private String originalAuthor;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 被回复者名字
     */
    private String answerName;

    /**
     * 回复者名字
     */
    private String respondentName;

    /**
     * 评论时间
     */
    private Date commentDate;

    /**
     * 点赞数
     */
    private int likes;

}
