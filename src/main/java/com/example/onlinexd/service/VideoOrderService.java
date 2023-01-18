package com.example.onlinexd.service;

import com.example.onlinexd.model.entity.VideoOrder;

import java.util.List;

/**
 * @author JaChen
 * @date 2023/1/15 20:43
 */
public interface VideoOrderService {

    int save(int userId, int videoId);

    List<VideoOrder> listOrderById(Integer userId);
}
