package com.seagull.myblog.utils;

import com.seagull.myblog.constant.RobotConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author Seagull_gby
 * @date 2019/5/16 11:20
 * Description: live2D模型工具类
 */
public class Live2dUtil {

    /**
     * 封装接口所需JSON
     * @param text 输入信息（聊天框）
     * @return JSON
     */
    public static JSONObject getLive2dJson(String text) {
        JSONObject live2d = new JSONObject();

        live2d.put("reqType", 0);

        JSONObject perception = new JSONObject();
        JSONObject inputText = new JSONObject();
        inputText.put("text", text);
        perception.put("inputText", inputText);
        live2d.put("perception", perception);

        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey", RobotConstants.API_KEY);
        userInfo.put("userId", RobotConstants.USER_ID);
        live2d.put("userInfo", userInfo);

        return live2d;
    }

    /**
     * 调用指定live2D API接口
     * @param live2d JSON
     * @return 机器人回复信息
     */
    public static String sendMsgOfLive2d(JSONObject live2d) {
        String live2dString = live2d.toString();

        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(RobotConstants.API_URL);
        CloseableHttpResponse response = null;

        try {
            post.setEntity(new ByteArrayEntity(live2dString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
                JSONObject jsonObject = JSONObject.fromObject(result);

                JSONArray ja = jsonObject.getJSONArray("results");
                System.out.println("ja ---> " + ja);
                JSONObject jo = ja.getJSONObject(ja.size() - 1);
                jsonObject = jo.getJSONObject("values");
                result = jsonObject.getString("text");
            }

            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if (entity != null) {
            long lenth = entity.getContentLength();
            if (lenth != -1 && lenth < 2048) {
                result = EntityUtils.toString(entity, "UTF-8");
            } else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while ((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }
}
