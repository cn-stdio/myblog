package com.seagull.myblog.controller;

import com.aliyuncs.exceptions.ClientException;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.User;
import com.seagull.myblog.service.RegisterService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Seagull_gby
 * @date 2019/3/23 11:21
 * Description: 注册跳转
 */

@Controller
public class RegisterControl {

    private static final String DEFAULT_BOY_IMG = "http://seaguller.oss-cn-beijing.aliyuncs.com/headImg/default/boy.jpg";
    private static final String DEFAULT_GIRL_IMG = "http://seaguller.oss-cn-beijing.aliyuncs.com/headImg/default/girl.jpg";

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserMapper userMapper;

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
    public JSONObject sendIdentifyCode(HttpServletRequest request, @RequestParam(value="type", required=false, defaultValue = "register") String type) throws ClientException {
        JSONObject sic = new JSONObject();

        String phone = request.getParameter("phone");
        if(type.equals("safety")) {
            sic = registerService.sendPhoneCode(phone, 2);
        } else if(type.equals("back")) {
            sic = registerService.sendPhoneCode(phone, 3);
        } else {
            sic = registerService.sendPhoneCode(phone, 1);
        }

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
     * 手机号存在检查
     * @param request 请求域
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/phoneExistCheck")
    public JSONObject phoneExistCheck(HttpServletRequest request) {
        JSONObject pc = new JSONObject();

        pc = registerService.phoneExistCheck(request.getParameter("phone"));

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

    /**
     * 注册用户
     * @param request 请求域
     * @return 页面
     */
    @RequestMapping("/registerUser")
    public String registerUser(HttpServletRequest request) {
        User user = new User();

        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setPhone(request.getParameter("phone"));

        String sex = request.getParameter("gender");
        if(sex.equals("male")) {
            user.setGender(1);
            user.setImageUrl(DEFAULT_BOY_IMG);
        } else {
            user.setGender(0);
            user.setImageUrl(DEFAULT_GIRL_IMG);
        }

        registerService.insertUser(user);

        return "registerSuccess";
    }

    /**
     * 密码找回
     * @param request 请求域
     * @return 页面
     */
    @RequestMapping("/retrievePassword")
    public String retrievePassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userMapper.updatePasswordByPhone(phone, encoder.encode(password));

        return "retrieveSuccess";
    }
}
