package com.seagull.myblog.utils;

import com.github.pagehelper.util.StringUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Seagull_gby
 * @date 2019/3/7 20:39
 * Description: base64转图片工具类
 */

public class Base64ToImageUtil {

    /**
     * 本地图片转换为base64字符串
     * @param imgFile 本地图片链接
     * @return base64字符串
     */
    public static String ImageToBase64ByLocal(String imgFile) {

        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 对字节数组Base64编码 */
        BASE64Encoder encoder = new BASE64Encoder();

        /* 返回Base64编码过的字节数组字符串 */
        return encoder.encode(data);
    }

    /**
     * 在线图片转换成base64字符串
     * @param imgURL 图片线上路径
     * @return base64字符串
     */
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    /**
     * base64字符串转换成图片
     * @param imgStr base64字符串
     * @param imgFilePath 图片存放路径
     * @return 图片文件
     */
    public static File Base64ToImage(String imgStr, String imgFilePath) {

        if (StringUtil.isEmpty(imgStr)) {
            return null;
        }

        imgStr = imgStr.substring(imgStr.indexOf(",")+1);

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return new File(imgFilePath);
        } catch (Exception e) {
            return null;
        }

    }
}