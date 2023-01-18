package com.example.onlinexd.controller;

import com.example.onlinexd.model.entity.Video;
import com.example.onlinexd.model.entity.VideoBanner;
import com.example.onlinexd.service.VideoService;
import com.example.onlinexd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author JaChen
 * @date 2022/11/29 22:56
 */
@CrossOrigin  // 开启跨域
@RestController
@RequestMapping("/api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;


    /**
     * 获取视频轮播图
     * @return
     */
    @GetMapping("list_banner")
    public JsonData listBanner(){

        List<VideoBanner> bannerList = videoService.listBanner();

        return JsonData.buildSuccess(bannerList);
    }


    /**
     * 获取视频列表
     * @return
     */
    @RequestMapping("list")
    public JsonData queryVideoList(){

        List<Video> videoList = videoService.queryVideoList();

        return JsonData.buildSuccess(videoList);

    }

    /**
     * 根据id获取视频
     * @param videoId
     * @return
     */
    @GetMapping("query_detail_by_id")
    public JsonData queryDetailById(@RequestParam(value = "video_id",required = true) Integer videoId){

//        int i = 1/0;

        Video video = videoService.queryDetailById(videoId);

        return JsonData.buildSuccess(video);
    }


    /**
     * 删除指定id的视频
     * @param videoId
     * @return
     */
    @DeleteMapping("del_by_id")
    public JsonData delById(@RequestParam("video_id") int videoId){
        int delete = videoService.delete(videoId);
        return JsonData.buildSuccess(delete);
    }


    /**
     * 更新指定id的视频
     * @param video
     * @return
     */
    @PutMapping("update_by_id")
    public JsonData update(@RequestBody Video video){

        int update = videoService.update(video);
        return JsonData.buildSuccess(update);
    }


    /**]
     * 添加视频
     * @param video
     * @return
     */
    @PostMapping("save")
    public JsonData save(@RequestBody Video video){

        int save = videoService.save(video);
        return JsonData.buildSuccess(save);

    }




}
