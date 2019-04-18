package com.seagull.myblog.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.seagull.myblog.constant.AliyunClientConstants;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Seagull_gby
 * @date 2019/3/22 15:38
 * Description: 阿里云连接工具类
 */

public class AliyunClientUtil {

    /**
     * 阿里云OSS API的内或外网域名
     */
    private static String ENDPOINT = "oss-cn-beijing.aliyuncs.com";

    /**
     * 阿里云API的密钥Access Key ID
     */
    private static String ACCESS_KEY_ID = AliyunClientConstants.ACCESS_KEY_ID_RAM;

    /**
     * 阿里云API的密钥Access Key Secret
     */
    private static String ACCESS_KEY_SECRET = AliyunClientConstants.ACCESS_KEY_SECRET_RAM;

    /**
     * 阿里云OSS API的bucket名称
     */
    private static String BACKET_NAME = "seaguller";

    /**
     * 阿里云短信服务修改密码模板CODE
     */
    private static String SAFETY_PHONE_TEMPLATE = "SMS_163431859";

    /**
     * 阿里云短信服务注册模板CODE
     */
    private static String REGISTER_PHONE_TEMPLATE = "SMS_161592275";

    /**
     * 阿里云短信服务找回密码模板CODE
     */
    private static String RETRIEVE_PHONE_TEMPLATE = "SMS_163705178";

    /**
     * 阿里云短信单发服务
     * @param phone 电话号码
     * @param code 验证码
     * @param type 模板选择（1为注册，2为修改密码，3为找回密码）
     * @return 目标JSON
     * @throws ClientException
     */
    public static SendSmsResponse sendSms(String phone, int code, int type) throws ClientException {
        /* 设置超时时间 */
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        /* 初始化ascClient需要的几个参数 */
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";

        final String accessKeyId = ACCESS_KEY_ID;
        final String accessKeySecret = ACCESS_KEY_SECRET;

        /* 初始化ascClient,暂时不支持多region */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        /* 组装请求对象 */
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phone);
        request.setSignName("Seaguller");
        if(type == 1) {
            request.setTemplateCode(REGISTER_PHONE_TEMPLATE);
        } else if(type == 2) {
            request.setTemplateCode(SAFETY_PHONE_TEMPLATE);
        } else {
            request.setTemplateCode(RETRIEVE_PHONE_TEMPLATE);
        }
        JSONObject smsJSON = new JSONObject();
        smsJSON.put("code", code);
        request.setTemplateParam(String.valueOf(smsJSON));

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            System.out.println("短信发送成功！");
        } else {
            System.out.println("短信发送失败！");
        }

        return sendSmsResponse;
    }

    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static  OSSClient getOSSClient(){
        return new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param bucketName  存储空间
     * @param folder  模拟文件夹名 如"seaguller/"
     * @param key 文件夹下的文件名 如："cake.jpg"
     */
    public static void deleteFile(String bucketName, String folder, String key){
        OSSClient ossClient = AliyunClientUtil.getOSSClient();
        ossClient.deleteObject(bucketName, folder + key);
        ossClient.shutdown();
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static  String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    /**
     * 上传图片至OSS
     * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param bucketName  存储空间
     * @param folder 模拟文件夹名 如"seaguller/"
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObjectOSS(File file, String bucketName, String folder) {
        OSSClient ossClient = AliyunClientUtil.getOSSClient();
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ossClient.shutdown();
        return resultStr;
    }

    /**
     * 下载文件
     * @param bucketName 存储空间
     * @param fileName 文件路径名
     */
    public static void downloadFile(String bucketName, String fileName) {
        OSSClient ossClient = AliyunClientUtil.getOSSClient();

        ossClient.getObject(new GetObjectRequest(bucketName, fileName), new File("D:\\picture\\faceJudge.jpg"));
        ossClient.shutdown();
    }

    /**
     * 返回外网访问URL
     * @param bucket 存储空间名
     * @param folderName 文件夹名
     * @param fileName 文件名
     * @return URL
     */
    public static String getUrl(String bucket, String folderName, String fileName) {
        String url = "https://" + bucket + "."+ ENDPOINT +"/" + folderName + "/" + fileName;

        return url;
    }

}
