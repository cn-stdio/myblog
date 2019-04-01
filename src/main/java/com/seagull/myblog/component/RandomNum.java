package com.seagull.myblog.component;

import org.springframework.stereotype.Component;

/**
 * @author Seagull_gby
 * @date 2019/3/22 15:20
 * Description: 随机数
 */

@Component
public class RandomNum {

    /**
     * 获取6位随机数
     * @return
     */
    public int getSixRandomNum() {
        int randomNum;

        randomNum = (int) ((Math.random()*9+1) * 100000);

        return randomNum;
    }

    /**
     * 获取3位随机数
     * @return
     */
    public int getThreeRandomNum() {
        int randomNum;

        randomNum = (int) ((Math.random()*9+1) * 100);

        return randomNum;
    }
}
