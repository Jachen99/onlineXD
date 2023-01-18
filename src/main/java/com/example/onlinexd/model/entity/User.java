package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 *    TODO:用户信息
 *
 *
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `name` varchar(128) DEFAULT NULL COMMENT '昵称',
 *   `pwd` varchar(124) DEFAULT NULL COMMENT '密码',
 *   `head_img` varchar(524) DEFAULT NULL COMMENT '头像',
 *   `phone` varchar(64) DEFAULT '' COMMENT '手机号',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *
 * @author JaChen
 * @date 2022/11/29 22:31
 */
@Data
@Builder
public class User {

    private Integer id;

    private String name;

    // @JsonIgnore 注解可以让 Jackson 在序列化
    // 和反序列化 Java 对象时忽略某些字段，
    // 这样就可以保证一些敏感信息不会被泄露。
    //@JsonIgnore
    private String pwd;

    private String headImg;

    private String phone;

    // 格式化返回前端的数据
    @JsonProperty("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

}
