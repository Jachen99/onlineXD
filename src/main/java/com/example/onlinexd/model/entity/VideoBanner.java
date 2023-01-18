package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 *   TODO：视频轮播图
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `url` varchar(256) DEFAULT NULL COMMENT '跳转地址',
 *   `img` varchar(256) DEFAULT NULL COMMENT '图片地址',
 *   `create_time` datetime DEFAULT NULL,
 *   `weight` int(11) DEFAULT NULL COMMENT '数字越小排越前',
 *    PRIMARY KEY (`id`)
 * @author JaChen
 * @date 2022/11/29 20:55
 */
@Data
public class VideoBanner {

    private Integer id;

    private String url;

    private String img;

    @JsonProperty("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    private Date creatTime;

    private Integer weight;

}
