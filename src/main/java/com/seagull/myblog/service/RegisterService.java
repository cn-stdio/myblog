package com.seagull.myblog.service;

import com.aliyuncs.exceptions.ClientException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:23
 * Description: 注册接口
 */

@Service
public interface RegisterService {

    /**
     * 发送手机验证码
     * @param phone 手机号码
     * @return JSON
     */
    JSONObject sendPhoneCode(String phone) throws ClientException;

    /**
     * 昵称注册重复检查
     * @param name 昵称
     * @return JSON
     */
    JSONObject nameRepeatCheck(String name);

    /**
     * 手机号注册重复检查
     * @param phone 手机号
     * @return JSON
     */
    JSONObject phoneRepeatCheck(String phone);

    /**
     * 验证码匹配检查
     * @param phone 手机号
     * @param captcha 验证码
     * @return JSON
     */
    JSONObject captchaCheck(String phone, int captcha);

}
