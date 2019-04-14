package com.seagull.myblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seagull.myblog.mapper.ArticleMapper;
import com.seagull.myblog.mapper.InformationMapper;
import com.seagull.myblog.mapper.UserMapper;
import com.seagull.myblog.model.ReplyInformation;
import com.seagull.myblog.service.InformationService;
import com.seagull.myblog.utils.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seagull_gby
 * @date 2019/4/13 21:42
 * Description: 消息实现类
 */

@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void getUserRepliedInformation(String userId, JSONObject informationJson, int rows, int pageNum) {
        JSONArray data = new JSONArray();
        TimeUtil timeUtil = new TimeUtil();

        PageHelper.startPage(pageNum, rows);
        List<ReplyInformation> informations = new ArrayList<>();
        informations = informationMapper.queryAllReplyInfomation(userId);
        PageInfo<ReplyInformation> pageInfo = new PageInfo<>(informations);

        if(informations.isEmpty()) {
            informationJson.put("msg", "noReply");
            return;
        }

        informations.forEach(information -> {
            JSONObject dataUnit = new JSONObject();

            dataUnit.put("title", articleMapper.queryTitleByArticleId(information.getArticleId()));
            dataUnit.put("articleId", information.getArticleId());

            try {
                dataUnit.put("time", timeUtil.getFormatDateOfyMd(information.getReplyTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dataUnit.put("respondentName", userMapper.queryNameById(information.getRespondentId()));
            dataUnit.put("answerName", userMapper.queryNameById(userId));
            dataUnit.put("content", information.getContent());

            data.add(dataUnit);
        });

        informationJson.put("data", data);
        informationJson.put("replyNum", pageInfo.getTotal());
        informationJson.put("msg", "success");
    }
}

