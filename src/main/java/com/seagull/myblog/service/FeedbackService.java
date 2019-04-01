package com.seagull.myblog.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/1 15:08
 * Description: 反馈接口
 */

@Service
public interface FeedbackService {

    /**
     * 插入反馈记录
     * @param msg 信息
     * @param contact 联系方式
     * @return JSON
     */
    public JSONObject insertFeedback(String msg, String contact);
}
