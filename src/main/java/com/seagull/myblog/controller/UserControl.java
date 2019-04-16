package com.seagull.myblog.controller;

import com.seagull.myblog.mapper.InformationMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.User;
import com.seagull.myblog.service.FeedbackService;
import com.seagull.myblog.service.InformationService;
import com.seagull.myblog.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Seagull_gby
 * @date 2019/4/11 14:56
 * Description:
 */

@Controller
public class UserControl {

    private static final String DEFAULT_BOY_IMG = "http://seaguller.oss-cn-beijing.aliyuncs.com/headImg/default/boy.jpg";
    private static final String DEFAULT_GIRL_IMG = "http://seaguller.oss-cn-beijing.aliyuncs.com/headImg/default/girl.jpg";

    @Autowired
    private UserService userService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public String userJump() {
        return "backStageUser";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/updateUserInformation")
    public JSONObject updateUserInformation(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject userMsg = new JSONObject();
        userMsg.put("code", 200);

        if(principal==null) {
            userMsg.put("msg", "noLogin");
        } else {
            User user = new User();

            String username = request.getParameter("name");
            String realName = request.getParameter("realName");
            String sexString = request.getParameter("sex");
            String birthday = request.getParameter("birthday");
            String email = request.getParameter("email");
            String privateMsg = request.getParameter("privateMsg");
            int sex = 0;
            if(sexString.equals("male")) {
                sex = 1;
            }

            String headImg = request.getParameter("headImg");
            int headImgFlag = -1;
            if(headImg.equals(DEFAULT_BOY_IMG) || headImg.equals(DEFAULT_GIRL_IMG)) {
                headImgFlag = 1;

                if(sex == 0) {
                    headImg = DEFAULT_GIRL_IMG;
                } else {
                    headImg = DEFAULT_BOY_IMG;
                }
            } else {
                headImg = "-1";
            }

            if(realName.equals("-1")) {
                realName = "";
            }
            if(birthday.equals("-1")) {
                birthday = "";
            }
            if(email.equals("-1")) {
                email = "";
            }
            if(privateMsg.equals("-1")) {
                privateMsg = "";
            }

            user.setUserName(username);
            user.setName(realName);
            user.setGender(sex);
            user.setBirthday(birthday);
            user.setEmail(email);
            user.setIntroduce(privateMsg);
            user.setImageUrl(headImg);
            user.setId(principal.getName());

            headImg = userService.updateUser(user);
            userMsg.put("msg", "success");
            userMsg.put("headImg", headImg);
            userMsg.put("headImgFlag", headImgFlag);
        }

        return userMsg;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/getUserInformation")
    public JSONObject getUserInformation(@AuthenticationPrincipal Principal principal) {
        JSONObject userInformation = new JSONObject();
        userInformation.put("code", 200);

        if(principal==null) {
            userInformation.put("msg", "noLogin");
        } else {
            userService.getUserInformation(userInformation, principal.getName());
            userInformation.put("msg", "success");
        }

        return userInformation;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/updateUserImg")
    public JSONObject updateUserImg(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject updateResult = new JSONObject();
        updateResult.put("code", 200);

        if(principal==null) {
            updateResult.put("msg", "noLogin");
        } else {
            String headImg = userService.updateUserImg(principal.getName(), request.getParameter("headImg"));
            updateResult.put("headImg", headImg);
            updateResult.put("msg", "success");
        }

        return updateResult;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/updatePassword")
    public JSONObject updatePassword(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject updateResult = new JSONObject();
        updateResult.put("code", 200);

        if(principal==null) {
            updateResult.put("msg", "noLogin");
        } else {
            userService.updatePassword(principal.getName(), request.getParameter("password"));
            updateResult.put("msg", "success");
        }

        return updateResult;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/getUserReplied")
    public JSONObject getUserReplyInformation(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject informationJson = new JSONObject();
        informationJson.put("code", 200);

        if(principal==null) {
            informationJson.put("msg", "noLogin");
        } else {
            int rows = Integer.valueOf(request.getParameter("rows"));
            int pageNum = Integer.valueOf(request.getParameter("pageNum"));
            informationService.getUserRepliedInformation(principal.getName(), informationJson, rows, pageNum);
        }

        return informationJson;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/getUserReplyNum")
    public JSONObject getUserReplyNum(@AuthenticationPrincipal Principal principal) {
        JSONObject rn = new JSONObject();
        rn.put("code", 200);

        if(principal==null) {
            rn.put("msg", "noLogin");
        } else {
            int replyNum = informationMapper.queryReplyInformationCount(principal.getName());
            rn.put("msg", "success");
            rn.put("replyNum", replyNum);
        }

        return rn;
    }

    /**
     * 昵称检查（个人中心）
     * @param request 请求域
     * @param principal 用户
     * @return JSON
     */
    @ResponseBody
    @RequestMapping("/user/nameCheck")
    public JSONObject nameCheckOfUser(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject nc = new JSONObject();
        nc.put("code", 200);

        String name = request.getParameter("name");

        if(principal==null) {
            nc.put("msg", "noLogin");
        } else {
            int count = userMapper.queryRepeatNameCount(name, principal.getName());
            if(count==0) {
                nc.put("msg", "none");
            } else {
                nc.put("msg", "有人跟您的昵称重复了哟~");
            }
        }


        return nc;
    }

    /**
     * 插入悄悄话（个人中心）
     * @param request 请求域
     * @param principal 用户
     * @return JSON
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseBody
    @RequestMapping("/user/insertFeedback")
    public JSONObject insertFeedback(HttpServletRequest request, @AuthenticationPrincipal Principal principal) {
        JSONObject ifb = new JSONObject();

        if(principal==null) {
            ifb.put("code", 200);
            ifb.put("msg", "noLogin");

            return ifb;
        } else {
            ifb = feedbackService.insertFeedback(request.getParameter("msg"), principal.getName());
        }

        return ifb;
    }
}
