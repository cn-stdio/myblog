package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.ArticleCommentMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.Comment;
import com.seagull.myblog.model.User;
import com.seagull.myblog.model.UserCommentLikeRecord;
import com.seagull.myblog.service.ArticleCommentService;
import com.seagull.myblog.utils.TimeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:31
 * Description: 评论功能跳转
 */
@RestController
public class CommentControl {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 返回所有评论（若已登录同时返回点赞信息）
     * @param principal 用于获取目前登录用户
     * @param request 请求域
     * @return JSON
     */
    @RequestMapping("/getArticleComment")
    public JSONObject getArticleComment(@AuthenticationPrincipal Principal principal, HttpServletRequest request) throws ParseException {
        JSONObject comment = new JSONObject();

        long articleId = Long.valueOf(request.getParameter("articleId"));
        String userId = "0";
        if(principal != null) {
            userId = principal.getName();
        }

        comment = articleCommentService.commentReturn(articleId, userId);

        return comment;
    }

    /**
     * 评论点赞操作
     * @param request 请求域
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/likeComment")
    public JSONObject likeComment(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject returnJudge = new JSONObject();
        returnJudge.put("code", 200);

        long articleId = Long.valueOf(request.getParameter("articleId"));
        String selfId = request.getParameter("selfId");

        if(principal==null) {
            returnJudge.put("msg", "noLogin");
            return returnJudge;
        } else {
            /* 搜索该用户是否点过赞（理论上点过赞不会进入，防止有人直接访问该API） */
            int count = articleCommentMapper.queryCommentLikeCountById(principal.getName(), articleId, selfId);
            if(count == 0) {
                articleCommentService.likeComment(articleId, selfId, principal.getName());
            } else {
                returnJudge.put("msg", "直接访问API可叭是一个好习惯哦~");
                return returnJudge;
            }
        }

        returnJudge.put("msg", "success");
        return returnJudge;
    }

    /**
     * 插入评论操作
     * @param request 请求域
     * @param principal 用于获取目前登陆用户
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/insertComment")
    public JSONObject insertComment(HttpServletRequest request, @AuthenticationPrincipal Principal principal) throws ParseException {
        JSONObject returnComment = new JSONObject();
        returnComment.put("code", 200);

        if(principal==null) {
            returnComment.put("msg", "noLogin");
            return returnComment;
        } else {
            Comment comment = new Comment();
            comment.setArticleId(Long.valueOf(request.getParameter("articleId")));
            comment.setSelfId(request.getParameter("selfId"));
            comment.setAnswerName(userMapper.queryIdByName(request.getParameter("answerName")));
            comment.setRespondentName(principal.getName());
            comment.setCommentContent(request.getParameter("content"));

            articleCommentService.insertComment(comment);
        }

        returnComment.put("msg", "success");
        return returnComment;
    }
}
