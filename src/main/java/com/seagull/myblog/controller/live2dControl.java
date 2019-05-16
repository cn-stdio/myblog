package com.seagull.myblog.controller;

import com.seagull.myblog.utils.Live2dUtil;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seagull_gby
 * @date 2019/5/16 9:50
 * Description: live2D 链接处理
 */

@RestController
public class live2dControl {

    @RequestMapping("/live2d")
    public JSONObject live2d(@RequestParam("info") String text) {
        JSONObject live2d = new JSONObject();
        live2d = Live2dUtil.getLive2dJson(text);

        String result = Live2dUtil.sendMsgOfLive2d(live2d);

        JSONObject data = new JSONObject();
        data.put("code", 200);
        data.put("msg", "success");
        data.put("text", result);

        return data;
    }

}
