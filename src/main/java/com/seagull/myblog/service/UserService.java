package com.seagull.myblog.service;

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
}
