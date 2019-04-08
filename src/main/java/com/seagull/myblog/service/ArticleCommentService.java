package com.seagull.myblog.service;

import com.seagull.myblog.model.Comment;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:22
 * Description: 文章评论功能接口
 */

@Service
public interface ArticleCommentService {

    /**
     * 返回所有评论
     * @param articleId 目标文章Id
     * @param userId 用户Id
     * @return
     */
    public JSONObject commentReturn(long articleId, String userId) throws ParseException;

    /**
     * 添加一条评论
     * @param comment 评论
     */
    public void insertComment(Comment comment);

    /**
     * 评论点赞
     * @param articleId 目标评论所在文章Id
     * @param selfId 目标评论层级
     * @param userId 登录用户ID
     */
    public void likeComment(long articleId, String selfId, String userId);
}
