package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2019/4/13 21:16
 * Description: 回复消息实体
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyInformation {

    /**
     * 独特标识id
     */
    private int id;

    /**
     * 文章ID
     */
    private long articleId;

    /**
     * 回复者ID
     */
    private String respondentId;

    /**
     * 被回复者ID
     */
    private String answerId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 消息状态（被阅读为1，未被阅读为0）
     */
    private int state;
}
