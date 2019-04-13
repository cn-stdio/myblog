package com.seagull.myblog.service;

import com.seagull.myblog.model.User;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/1 13:14
 * Description: 用户接口
 */

@Service
public interface UserService {

    /**
     * 获得用户昵称
     * @param userId 用户ID
     * @return JSON
     */
    public JSONObject getUserName(String userId);

    /**
     * 更新用户个人信息
     * @param user 用户
     * @return 用户头像URL
     */
    public String updateUser(User user);

    /**
     * 获取用户信息
     * @param userInformation 用户信息JSON
     * @param userId 用户ID
     */
    public void getUserInformation(JSONObject userInformation, String userId);

    /**
     * 更新用户头像
     * @param userId 用户ID
     * @param imgBase64 图片base64格式
     * @return 用户头像外链
     */
    public String updateUserImg(String userId, String imgBase64);

    /**
     * 密码修改
     * @param userId 用户ID
     * @param password 新密码
     */
    public void updatePassword(String userId, String password);
}
