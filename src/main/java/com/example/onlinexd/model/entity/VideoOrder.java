package com.example.onlinexd.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `out_trade_no` varchar(64) DEFAULT NULL COMMENT '订单唯一标识',
 *   `state` int(11) DEFAULT NULL COMMENT '0表示未支付，1表示已支付',
 *   `create_time` datetime DEFAULT NULL COMMENT '订单生成时间',
 *   `total_fee` int(11) DEFAULT NULL COMMENT '支付金额，单位分',
 *   `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
 *   `video_title` varchar(256) DEFAULT NULL COMMENT '视频标题',
 *   `video_img` varchar(256) DEFAULT NULL COMMENT '视频图片',
 *   `user_id` int(12) DEFAULT NULL COMMENT '用户id',
 *
 * @author JaChen
 * @date 2022/11/29 22:34
 */
@Data
public class VideoOrder {

    private Integer id;

    @JsonProperty("out_trade_no")
    private String outTradeNo;

    private String state;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") // 格式化返回前端的数据
    private Date createTime;

    @JsonProperty("total_free")
    private Integer totalFree;

    @JsonProperty("video_id")
    private Integer videoId;

    @JsonProperty("video-title")
    private String videoTitle;

    @JsonProperty("video_img")
    private String videoImg;

    @JsonProperty("user_id")
    private Integer userId;

}
