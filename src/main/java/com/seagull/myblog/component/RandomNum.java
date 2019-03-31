package com.seagull.myblog.component;

import org.springframework.stereotype.Component;

/**
 * @author Seagull_gby
 * @date 2019/3/22 15:20
 * Description: 随机数
 */

@Component
public class RandomNum {

    public int getSixRandomNum() {
        int randomNum;

        randomNum = (int) ((Math.random()*9+1) * 100000);

        return randomNum;
    }
}
