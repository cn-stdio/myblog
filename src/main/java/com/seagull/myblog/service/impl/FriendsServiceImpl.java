package com.seagull.myblog.service.impl;

import com.seagull.myblog.mapper.FriendsMapper;
import com.seagull.myblog.model.Friend;
import com.seagull.myblog.service.FriendsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/5/26 13:14
 * Description: 友链接口实现
 */

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public JSONObject queryAllFriends() {
        JSONObject friendsJSON = new JSONObject();
        List<Friend> friends = new ArrayList<>();

        friends = friendsMapper.queryAllFriends();
        if (friends.isEmpty()) {
            friendsJSON.put("msg", "noFriends");
        } else {
            JSONArray data = new JSONArray();
            friends.forEach(friend -> {
                JSONObject dataUnit = new JSONObject();
                dataUnit.put("id", friend.getId());
                dataUnit.put("name", friend.getName());
                dataUnit.put("introduce", friend.getIntroduce());
                dataUnit.put("url", friend.getUrl());
                dataUnit.put("img", friend.getHeadImg());

                data.add(dataUnit);
            });

            friendsJSON.put("msg", "success");
            friendsJSON.put("data", data);
        }

        friendsJSON.put("code", 200);
        return friendsJSON;
    }

    @Override
    public JSONObject updateFriendById(Friend friend) {
        JSONObject friendsJSON = new JSONObject();

        friendsMapper.updateFriendById(friend);
        friendsJSON.put("code", 200);
        friendsJSON.put("msg", "success");
        return friendsJSON;
    }

    @Override
    public JSONObject insertFriend(Friend friend) {
        JSONObject friendJSON = new JSONObject();

        friendsMapper.insertFriend(friend);

        friendJSON.put("code", 200);
        friendJSON.put("msg", "success");
        return friendJSON;
    }
}
