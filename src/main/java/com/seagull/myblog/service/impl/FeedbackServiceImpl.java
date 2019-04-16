package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.FeedbackMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.Feedback;
import com.seagull.myblog.service.FeedbackService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/1 15:09
 * Description: 反馈接口实现
 */

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject insertFeedback(String msg, String contact) {
        JSONObject ifb = new JSONObject();
        ifb.put("code", 200);

        Feedback feedback = new Feedback();
        feedback.setMsg(msg);
        feedback.setContact(contact);
        int count = feedbackMapper.insertFeedback(feedback);
        if(count == 0) {
            ifb.put("msg", "false");
            return ifb;
        }

        ifb.put("msg", "success");
        return ifb;
    }

    @Override
    public JSONObject getFeedback(int pageNum, int rows) {
        JSONObject ff = new JSONObject();
        ff.put("code", 200);

        PageHelper.startPage(pageNum, rows);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks = feedbackMapper.queryAllFeedback();
        PageInfo<Feedback> pageInfo = new PageInfo<>(feedbacks);

        if(feedbacks.isEmpty()) {
            ff.put("msg", "noFeedback");
            return ff;
        }

        JSONArray data = new JSONArray();
        feedbacks.forEach(feedback -> {
            JSONObject dataUnit = new JSONObject();

            dataUnit.put("state", feedback.getState());
            feedbackMapper.updateStateById(feedback.getId());
            if(feedback.getContact().equals("无")) {
                dataUnit.put("contact", "未添加联系人");
            } else {
                String userName = userMapper.queryNameById(feedback.getContact());
                if(userName==null) {
                    dataUnit.put("contact", feedback.getContact());
                } else {
                    dataUnit.put("contact", userName);
                }
            }
            dataUnit.put("content", feedback.getMsg());

            data.add(dataUnit);
        });

        ff.put("msg", "success");
        ff.put("data", data);
        ff.put("feedbackNum", pageInfo.getTotal());
        return ff;
    }
}
