package com.example.onlinexd.service;

import com.example.onlinexd.model.entity.Video;
import com.example.onlinexd.model.entity.VideoBanner;

import java.util.List;

/**
 * @author JaChen
 * @date 2022/11/29 22:51
 */
public interface VideoService {

    List<Video> queryVideoList();

    List<VideoBanner> listBanner();

    Video queryDetailById(Integer videoId);

    int update(Video Video);

    int delete(int id);

    int save(Video video);
}
