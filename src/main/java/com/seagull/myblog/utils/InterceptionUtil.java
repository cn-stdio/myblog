package com.seagull.myblog.utils;

/**
 * @author Seagull_gby
 * @date 2018/9/14 15:04
 * Description: 截取摘要工具类
 */
public class InterceptionUtil {

    /**
     * 截取文章摘要（最多生成前80个字符）
     * @param articleContent 文章内容
     * @return 文章摘要
     */
    public String interceptionArticle(String articleContent) {

        MoveTagUtil moveTagUtil = new MoveTagUtil();

        articleContent = moveTagUtil.removeTag(articleContent);

        if(articleContent.length() >= 80) {
            articleContent = articleContent.substring(0, 80);
        }
        articleContent = articleContent + "...";

        return articleContent;
    }

    /**
     * 截取回复内容（前31个字符）
     * @param reply 回复内容
     * @return 截取后的内容
     */
    public String interceptionReply(String reply) {

        if(reply.length() >= 31) {
            reply = reply.substring(0, 31);
        }

        reply = reply + "...";

        return reply;
    }
}