package com.seagull.myblog.service;

import com.aliyuncs.exceptions.ClientException;
import com.seagull.myblog.model.User;
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
     * @param type 服务类型（1为注册，2为修改密码）
     * @return JSON
     */
    JSONObject sendPhoneCode(String phone, int type) throws ClientException;

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

    /**
     * 用户注册
     * @param user 用户
     */
    void insertUser(User user);

}
