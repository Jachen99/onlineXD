package com.example.onlinexd.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JaChen
 * @date 2023/1/15 20:22
 */
@Data
public class VideoOrderRequest {

    // @JsonProperty注解是Jackson库中的注解，
    // 用于在将Java对象转换为JSON时指定序列化该属性时使用的名称。
    @JsonProperty("video_id")
    private int videoId;

}
