package com.seagull.myblog.utils;

/**
 * @author Seagull_gby
 * @date 2018/9/14 15:04
 * Description: 截取文章摘要
 */
public class InterceptionArticleUtil {

    /**
     * 截取文章摘要（最多生成前30个字符）
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
}