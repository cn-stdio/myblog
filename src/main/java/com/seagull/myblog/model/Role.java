package com.seagull.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seagull_gby
 * @date 2019/3/21 20:59
 * Description: 权限实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * ID
     */
    private String id;

    /**
     * 权限名字
     */
    private String name;
}
