package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 * TODO： 集对象
 *
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `title` varchar(524) DEFAULT NULL COMMENT '集标题',
 *   `num` int(10) DEFAULT NULL COMMENT '第几集,全局顺序',
 *   `ordered` int(11) DEFAULT NULL COMMENT '顺序，章里面的顺序',
 *   `play_url` varchar(256) DEFAULT NULL COMMENT '播放地址',
 *   `chapter_id` int(11) DEFAULT NULL COMMENT '章节主键id',
 *   `free` tinyint(2) DEFAULT '0' COMMENT '0表示免费，1表示首付',
 *   `video_id` int(10) DEFAULT NULL COMMENT '视频id',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *
 * @author JaChen
 * @date 2022/11/29 22:26
 */
@Data
public class Episode {

    private Integer id;

    private String title;

    private Integer num;

    private Integer ordered;

    @JsonProperty("play_url")
    private String playUrl;

    private Integer chapterId;

    private Integer free;

    private Integer videoId;

    @JsonProperty("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    private Date createTime;

}
