package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 *   TODO： 视频对象
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `title` varchar(524) DEFAULT NULL COMMENT '视频标题',
 *   `summary` varchar(1026) DEFAULT NULL COMMENT '概述',
 *   `cover_img` varchar(524) DEFAULT NULL COMMENT '封面图',
 *   `price` int(11) DEFAULT NULL COMMENT '价格,分',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `point` double(11,2) DEFAULT '8.70' COMMENT '默认8.7，最高10分',
 *
 * @author JaChen
 * @date 2022/11/29 21:04
 */
@Data
public class Video {

    private Integer id;

    private String title;

    private String summary;

    @JsonProperty("cover_img")
    private String coverImg;

    private Integer price;

    @JsonProperty("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    private Date creatTime;

    private Double point;

    @JsonProperty("chapter_list")

    private List<Chapter> chapterList;

}
