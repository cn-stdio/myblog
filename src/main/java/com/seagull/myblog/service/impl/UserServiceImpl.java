package com.seagull.myblog.service.impl;

import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.User;
import com.seagull.myblog.service.UserService;
import com.seagull.myblog.utils.AliyunClientUtil;
import com.seagull.myblog.utils.Base64ToImageUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Seagull_gby
 * @date 2019/4/1 13:16
 * Description: 用户接口实现类
 */

@Service
public class UserServiceImpl implements UserService {

    private static final String IMG_FILE_PATH = "D:\\";

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getUserName(String userId) {
        JSONObject user = new JSONObject();

        String name = userMapper.queryNameById(userId);

        user.put("msg", "success");
        user.put("username", name);
        return user;
    }

    @Override
    public String updateUser(User user) {
        if(user.getImageUrl().equals("-1")) {
            user.setImageUrl(userMapper.queryImageById(user.getId()));
        }

        userMapper.updateUser(user);

        return user.getImageUrl();
    }

    @Override
    public void getUserInformation(JSONObject userInformation, String userId) {
        User user = userMapper.queryUserById(userId);

        userInformation.put("headImg", user.getImageUrl());
        userInformation.put("username", user.getUserName());
        userInformation.put("phone", user.getPhone());
        userInformation.put("realName", user.getName());
        userInformation.put("sex", user.getGender());
        userInformation.put("birthday", user.getBirthday());
        userInformation.put("email", user.getEmail());
        userInformation.put("introduce", user.getIntroduce());
    }

    @Override
    public String updateUserImg(String userId, String imgBase64) {
        String postfix = imgBase64.substring(imgBase64.indexOf("/")+1, imgBase64.indexOf(";"));

        File imgFile = Base64ToImageUtil.Base64ToImage(imgBase64, IMG_FILE_PATH + userId + "." + postfix);
        AliyunClientUtil.uploadObjectOSS(imgFile, "seaguller", "headImg/");

        String imgUrl = AliyunClientUtil.getUrl("seaguller", "headImg", userId + "." + postfix);

        userMapper.updateUserImg(userId, imgUrl);

        imgFile.delete();
        return imgUrl;
    }

    @Override
    public void updatePassword(String userId, String password) {
        /* 密码加密 */
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userMapper.updatePassword(userId, encoder.encode(password));
    }
}
