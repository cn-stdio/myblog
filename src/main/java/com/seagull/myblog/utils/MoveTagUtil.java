package com.seagull.myblog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Seagull_gby
 * @date 2018/9/14 14:38
 * Description: 去除文章标签
 */
public class MoveTagUtil {

    /**
     * 去除文章HTML标签
     * @param htmlStr 传入的HTML内容
     * @return
     */
    public String removeTag(String htmlStr) {
        String regexScript = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regexStyle = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regexHtml = "<[^>]+>";
        String regexSpace = "\\s+|\t|\r|\n";

        Pattern pScript = Pattern.compile(regexScript,
                Pattern.CASE_INSENSITIVE);
        Matcher mScript = pScript.matcher(htmlStr);
        htmlStr = mScript.replaceAll("");
        Pattern pStyle = Pattern
                .compile(regexStyle, Pattern.CASE_INSENSITIVE);
        Matcher mStyle = pStyle.matcher(htmlStr);
        htmlStr = mStyle.replaceAll("");
        Pattern pHtml = Pattern.compile(regexHtml, Pattern.CASE_INSENSITIVE);
        Matcher mHtml = pHtml.matcher(htmlStr);
        htmlStr = mHtml.replaceAll("");
        Pattern pSpace = Pattern
                .compile(regexSpace, Pattern.CASE_INSENSITIVE);
        Matcher mSpace = pSpace.matcher(htmlStr);
        htmlStr = mSpace.replaceAll(" ");

        return htmlStr;
    }
}
