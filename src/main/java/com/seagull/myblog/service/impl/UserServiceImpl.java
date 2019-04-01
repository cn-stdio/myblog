package com.seagull.myblog.service.impl;

import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/1 13:16
 * Description: 用户接口实现类
 */

@Service
public class UserServiceImpl implements UserService {

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
}
