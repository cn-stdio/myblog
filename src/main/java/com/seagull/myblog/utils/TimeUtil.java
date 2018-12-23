package com.seagull.myblog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2018/09/09
 * Description: 日期格式化工具类
 */
public class TimeUtil {

    /**
     * 将指定日期格式化为 年/月/日 时:分:秒 格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfymd(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 yyyy年MM月  格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfym(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy年MM月");
        String s = format.format(d);

        return s;
    }

    /**
     * 将 yyyy年MM月dd日 hh:mm:ss 格式的字符串转换为日期格式
     * @param s 指定字符串
     * @return
     * @throws ParseException
     */
    public Date getFormatString(String s) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = format.parse(s);

        return date;
    }
}
