package com.seagull.myblog.service.impl;

import com.seagull.myblog.service.InformationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author Seagull_gby
 * @date 2019/4/13 21:42
 * Description: 消息实现类
 */

@Service
public class InformationServiceImpl implements InformationService {

    @Override
    public void getUserRepliedInformation(String userId, JSONObject informationJson, int rows, int pageNum) {
        JSONArray data = new JSONArray();
        JSONObject dataUnit = new JSONObject();


        informationJson.put("data", data);
    }
}

