package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 *
 *
 *   TODO:章对象
 *
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
 *   `title` varchar(128) DEFAULT NULL COMMENT '章节名称',
 *   `ordered` int(11) DEFAULT NULL COMMENT '章节顺序',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *
 * @author JaChen
 * @date 2022/11/29 22:23
 */
@Data
public class Chapter {

    private Integer id;

    private String title;

    private Integer ordered;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    @JsonProperty("creat_time")
    private String createTime;

    @JsonProperty("episode_list")
    private List<Episode> episodeList;

}
