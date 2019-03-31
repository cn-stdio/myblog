package com.seagull.myblog.controller;

import com.aliyuncs.exceptions.ClientException;
import com.seagull.myblog.service.RegisterService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:21
 * Description: 注册跳转
 */

@Controller
public class RegisterControl {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String registerJump() {
        return "register";
    }

    @RequestMapping("/registerSuccess")
    public String registerSuccess() {
        return "registerSuccess";
    }

    /**
     * 发送手机验证码
     * @param request 请求域
     * @return JSON
     * @throws ClientException
     */
    @ResponseBody
    @RequestMapping("/sendIdentifyCode")
    public JSONObject sendIdentifyCode(HttpServletRequest request) throws ClientException {
        JSONObject sic = new JSONObject();

        String phone = request.getParameter("phone");
        sic = registerService.sendPhoneCode(phone);

        return sic;
    }

    /**
     * 昵称检查
     * @param request 请求域
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/nameCheck")
    public JSONObject nameCheck(HttpServletRequest request) {
        JSONObject nc = new JSONObject();

        nc = registerService.nameRepeatCheck(request.getParameter("name"));

        return nc;
    }

    /**
     * 手机号检查
     * @param request 请求域
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/phoneCheck")
    public JSONObject phoneCheck(HttpServletRequest request) {
        JSONObject pc = new JSONObject();

        pc = registerService.phoneRepeatCheck(request.getParameter("phone"));

        return pc;
    }

    /**
     * 验证码匹配检查
     * @param request 请求域
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/captchaCheck")
    public JSONObject captchaCheck(HttpServletRequest request) {
        JSONObject c = new JSONObject();

        String phone = request.getParameter("phone");
        int captcha = Integer.valueOf(request.getParameter("captcha"));
        c = registerService.captchaCheck(phone, captcha);

        return c;
    }
}
