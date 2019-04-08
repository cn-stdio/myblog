package com.seagull.myblog.service.impl;

import com.seagull.myblog.mapper.ArticleAttributeMapper;
import com.seagull.myblog.mapper.ArticleCommentMapper;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.Comment;
import com.seagull.myblog.model.UserCommentLikeRecord;
import com.seagull.myblog.service.ArticleCommentService;
import com.seagull.myblog.utils.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Seagull_gby
 * @date 2019/4/3 20:23
 * Description: 文章评论实现类
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleAttributeMapper articleAttributeMapper;

    /**
     * 返回文章评论实现
     * @param articleId 目标文章Id
     * @param userId 用户Id
     * @return 返回评论的JSONArray数组形式
     */
    @Override
    public JSONObject commentReturn(long articleId, String userId) throws ParseException {
        JSONObject commentReturn = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray commentArray = new JSONArray();
        JSONArray likeRecordArray = new JSONArray();

        /* 查询所有楼主评论（不包含回复层） */
        List<Comment> comments = articleCommentMapper.queryFloorCommentsById(articleId);
        /* 查询所有评论（包含回复层） */
        List<Comment> allComments = articleCommentMapper.queryCommentsByArticleId(articleId);
        /* 未登录直接返回noLogin，若登录则加入登录用户对层级点赞信息（预防重复点赞） */
        if(userId.equals("0")) {
            data.put("likeList", "noLogin");
        } else {
            List<UserCommentLikeRecord> likeRecords = articleCommentMapper.queryAllUserCommentLikeRecordByUserId(userId, articleId);
            likeRecords.forEach(likeRecord -> likeRecordArray.add(likeRecord.getSelfId()));
            data.put("likeList", likeRecordArray);
        }

        commentReturn.put("code", 200);

        if(comments.isEmpty()) {
            commentReturn.put("msg", "empty");
        } else {
            commentReturn.put("msg", "success");
            for (Comment comment : comments) {

                JSONObject returnComment = new JSONObject();

                returnComment.put("commentImg", userMapper.queryImageById(comment.getRespondentName()));
                returnComment.put("respondentName", userMapper.queryNameById(comment.getRespondentName()));
                returnComment.put("content", comment.getCommentContent());
                TimeUtil timeUtil = new TimeUtil();
                String date = timeUtil.getFormatDateOfyMdHm(comment.getCommentDate());
                returnComment.put("date", date);
                returnComment.put("like", comment.getLikes());
                returnComment.put("selfId", Integer.parseInt(comment.getSelfId()));

                JSONArray reply = new JSONArray();
                List<Comment> commentReplay = new ArrayList<>();
                /* 对所有评论遍历，寻找该层级的所有回复 */
                for (Comment comment2 : allComments) {
                    String[] selfIds = comment2.getSelfId().split(",");
                    if (selfIds[0].equals(comment.getSelfId()) && selfIds.length == 2) {
                        commentReplay.add(comment2);
                    }
                }
                /* 防止数据混乱将各层级数据排序 */
                Collections.sort(commentReplay, new Comparator<Comment>() {
                    @Override
                    public int compare(Comment c1, Comment c2) {
                        return new Integer(c1.getSelfId().split(",")[1]).compareTo(Integer.valueOf(c2.getSelfId().split(",")[1]));
                    }
                });
                for (Comment comment2 : commentReplay) {
                    JSONObject replyReturn = new JSONObject();
                    replyReturn.put("commentImg", userMapper.queryImageById(comment2.getRespondentName()));
                    replyReturn.put("respondentName", userMapper.queryNameById(comment2.getRespondentName()));
                    replyReturn.put("answerName", userMapper.queryNameById(comment2.getAnswerName()));
                    replyReturn.put("content", comment2.getCommentContent());
                    String date2 = timeUtil.getFormatDateOfyMdHm(comment2.getCommentDate());
                    replyReturn.put("date", date2);
                    replyReturn.put("like", comment2.getLikes());
                    replyReturn.put("selfId", Integer.parseInt(comment2.getSelfId().split(",")[1]));

                    reply.add(replyReturn);
                }

                returnComment.put("reply", reply);

                commentArray.add(returnComment);
            }
        }

        data.put("comment", commentArray);

        commentReturn.put("data", data);

        return commentReturn;
    }

    /**
     * 插入评论实现
     * @param comment 评论
     */
    @Override
    public void insertComment(Comment comment) {
        if(comment.getArticleId()==0) {
            comment.setOriginalAuthor("1554087772607972");
        } else {
            comment.setOriginalAuthor(articleMapper.queryArticleByArticleId(comment.getArticleId()).getAuthor());
        }

        String selfId = comment.getSelfId();
        String[] selfIds = selfId.split(",");

        if(selfIds.length == 1) {
            comment.setAnswerName(comment.getOriginalAuthor());
            articleAttributeMapper.updateArticleCommentById(comment.getArticleId());
        }

        articleCommentMapper.insertArticleCommen(comment);
    }

    /**
     * 评论点赞实现
     * @param articleId 目标评论所在文章Id
     * @param selfId 目标评论层级
     * @param userId 登录用户ID
     */
    @Override
    public void likeComment(long articleId, String selfId, String userId) {
        articleCommentMapper.updateCommentLikePlus(articleId, selfId);
        articleCommentMapper.insertUserCommentLikeRecord(userId, articleId, selfId);
    }

}
