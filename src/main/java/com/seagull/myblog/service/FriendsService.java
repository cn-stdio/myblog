package com.seagull.myblog.service;

import com.seagull.myblog.model.Friend;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/5/26 13:13
 * Description: 友链接口
 */

@Service
public interface FriendsService {

    /**
     * 查询全部友链信息
     * @return JSON
     */
    public JSONObject queryAllFriends();

    /**
     * 更新指定ID友链
     * @param friend 友链实体信息
     * @return JSON
     */
    public JSONObject updateFriendById(Friend friend);

    /**
     * 插入一条友链
     * @param friend 友链实体信息
     * @return JSON
     */
    public JSONObject insertFriend(Friend friend);

    /**
     * 删除指定友链信息
     * @param id 友链uuid
     * @return JSON
     */
    public JSONObject deleteFriendById(String id);
}
