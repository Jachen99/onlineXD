package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 *   播放记录
 *
 *  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `user_id` int(11) DEFAULT NULL,
 *   `video_id` int(11) DEFAULT NULL,
 *   `current_num` int(11) DEFAULT NULL COMMENT '当前播放第几集',
 *   `episode_id` int(11) DEFAULT NULL COMMENT '当前播放第几集视频id',
 *   `create_time` datetime DEFAULT NULL,
 */
@Data
public class PlayRecord {


    private Integer id;

    private Integer userId;

    private Integer videoId;

    private Integer currentNum;

    private Integer episodeId;

    @JsonProperty("creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    private Date createTime;

}
