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

    /**
     * 将日期格式转换为 yyyy-MM-dd HH:mm 格式的字符串
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfyMdHm(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 MM月dd日  格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfmd(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("MM月dd日");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 HH时  格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfh(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH时");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 HH:mm 格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfHHmm(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 yyyy-MM-dd 格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfyMd(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(d);

        return s;
    }

    /**
     * 将指定日期格式化为 HH 格式
     * @param d 指定日期
     * @return
     * @throws ParseException
     */
    public String getFormatDateOfHH(Date d) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH");
        String s = format.format(d);

        return s;
    }

    /**
     * 将时间戳转化为日期
     * @param t 时间戳
     * @return 日期
     */
    public Date getFormatDateOfTime(long t) {
        Date date = new Date(t);

        return date;
    }
}
