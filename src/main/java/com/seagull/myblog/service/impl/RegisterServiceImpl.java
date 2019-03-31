package com.seagull.myblog.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.seagull.myblog.component.RandomNum;
import com.seagull.myblog.mapper.RegisterMapper;
import com.seagull.myblog.service.RegisterService;
import com.seagull.myblog.service.redis.RedisService;
import com.seagull.myblog.utils.AliyunClientUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:24
 * Description: 注册接口实现
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RandomNum randomNum;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public JSONObject sendPhoneCode(String phone) throws ClientException {
        JSONObject spc = new JSONObject();

        int sixRandow = randomNum.getSixRandomNum();
        redisService.setAndTimeOut(phone, sixRandow, 180);

        SendSmsResponse sendSmsResponse = AliyunClientUtil.sendSms(phone, sixRandow);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            spc.put("code", 200);
            spc.put("msg", "success");
        } else {
            spc.put("code", sendSmsResponse.getCode());
            spc.put("msg", sendSmsResponse.getMessage());
        }

        return spc;
    }

    @Override
    public JSONObject nameRepeatCheck(String name) {
        JSONObject nrc = new JSONObject();
        nrc.put("code", 200);

        List<String> allNames = registerMapper.queryAllName();
        for (String n : allNames) {
            if(n.equals(name)) {
                nrc.put("msg", "有人跟您的昵称重复了鸭~");
                return nrc;
            }
        }

        nrc.put("msg", "success");
        return nrc;
    }

    @Override
    public JSONObject phoneRepeatCheck(String phone) {
        JSONObject prc = new JSONObject();
        prc.put("code", 200);

        List<String> allPhones = registerMapper.queryAllPhone();
        for (String p : allPhones) {
            if(p.equals(phone)) {
                prc.put("msg", "竟然拿注册过的手机号糊弄我！哼！");
                return prc;
            }
        }

        prc.put("msg", "success");
        return prc;
    }

    @Override
    public JSONObject captchaCheck(String phone, int captcha) {
        JSONObject cc = new JSONObject();
        cc.put("code", 200);

        if (redisService.hasKey(phone)) {
            int saveCaptcha = (int) redisService.get(phone);
            if (saveCaptcha != captcha) {
                cc.put("msg", "发给你的明明不是介个验证码！");
                return cc;
            }
        } else {
            cc.put("msg", "瞎输一个可是糊弄不过我的鸭！");
            return cc;
        }

        cc.put("msg", "success");
        return cc;
    }

}
