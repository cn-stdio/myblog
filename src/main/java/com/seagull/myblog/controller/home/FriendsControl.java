package com.seagull.myblog.controller.home;

import com.seagull.myblog.model.Friend;
import com.seagull.myblog.service.FriendsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Seagull_gby
 * @date 2019/3/14 11:28
 * Description: 友链跳转类
 */

@Controller
public class FriendsControl {

    @Autowired
    private FriendsService friendsService;

    @GetMapping("/friends")
    public String toFriends() {
        return "friends";
    }

    @ResponseBody
    @RequestMapping("/getFriends")
    public JSONObject getFriends() {
        JSONObject friends = new JSONObject();

        friends = friendsService.queryAllFriends();


        return friends;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/updateFriends")
    public JSONObject updateFriends(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject friends = new JSONObject();

        if (principal == null) {
            friends.put("code", 200);
            friends.put("msg", "noLogin");
            return friends;
        } else {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String introduce = request.getParameter("introduce");
            String url = request.getParameter("url");
            String img = request.getParameter("img");

            Friend friend = new Friend();
            friend.setId(id);
            friend.setName(name);
            friend.setIntroduce(introduce);
            friend.setUrl(url);
            friend.setHeadImg(img);

            friends = friendsService.updateFriendById(friend);
        }

        return friends;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/insertFriend")
    public JSONObject inserFriend(@AuthenticationPrincipal Principal principal, HttpServletRequest request) {
        JSONObject friends = new JSONObject();

        if (principal == null) {
            friends.put("code", 200);
            friends.put("msg", "noLogin");
            return friends;
        } else {
            String name = request.getParameter("name");
            String introduce = request.getParameter("introduce");
            String url = request.getParameter("url");
            String img = request.getParameter("img");

            Friend friend = new Friend();
            friend.setName(name);
            friend.setIntroduce(introduce);
            friend.setUrl(url);
            friend.setHeadImg(img);

            friends = friendsService.insertFriend(friend);
        }

        return friends;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    @RequestMapping("/deleteFriend")
    public JSONObject deleteFriend(@AuthenticationPrincipal Principal principal, @RequestParam("friendId") String id) {
        JSONObject friends = new JSONObject();

        if (principal == null) {
            friends.put("code", 200);
            friends.put("msg", "noLogin");
            return friends;
        } else {
            friends = friendsService.deleteFriendById(id);
        }

        return friends;
    }

}
