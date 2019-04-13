package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Seagull_gby
 * @date 2019/3/21 20:43
 * Description: 用户类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 独特标识ID
     */
    private String id;

    /**
     * 性别（男1女2）
     */
    private int gender;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像URL
     */
    private String imageUrl;

    /**
     * 权限
     */
    private Role role;

    /**
     * 最近登录日期
     */
    private Date recentLoginDate;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 个人介绍
     */
    private String introduce;
}
