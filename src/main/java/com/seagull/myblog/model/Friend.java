package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/5/26 13:10
 * Description: 友链实体
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点介绍
     */
    private String introduce;

    /**
     * 站主头像
     */
    private String headImg;

    /**
     * 站点链接
     */
    private String url;
}
