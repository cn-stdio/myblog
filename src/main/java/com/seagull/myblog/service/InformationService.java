package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/13 21:39
 * Description: 消息接口
 */

@Service
public interface InformationService {

    /**
     * 获取用户评论被回复消息
     * @param userId 用户ID
     * @param informationJson 消息JSON
     * @param rows  一页大小
     * @param pageNum  当前页数
     */
    public void getUserRepliedInformation(String userId, JSONObject informationJson, int rows, int pageNum);
}
