package com.seagull.myblog.service.impl;

import com.seagull.myblog.mapper.FeedbackMapper;
import com.seagull.myblog.model.Feedback;
import com.seagull.myblog.service.FeedbackService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/1 15:09
 * Description: 反馈接口实现
 */

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public JSONObject insertFeedback(String msg, String contact) {
        JSONObject ifb = new JSONObject();
        ifb.put("code", 200);

        Feedback feedback = new Feedback(msg, contact);
        int count = feedbackMapper.insertFeedback(feedback);
        if(count == 0) {
            ifb.put("msg", "false");
            return ifb;
        }

        ifb.put("msg", "success");
        return ifb;
    }
}
