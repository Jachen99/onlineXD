package com.example.onlinexd.service.impl;

import com.example.onlinexd.config.CacheKeyManager;
import com.example.onlinexd.model.entity.Video;
import com.example.onlinexd.model.entity.VideoBanner;
import com.example.onlinexd.mapper.VideoMapper;
import com.example.onlinexd.service.VideoService;
import com.example.onlinexd.utils.BaseCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author JaChen
 * @date 2022/11/29 22:52
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;


    /**
     * 获取轮播图列表 加了guava本地缓存
     * @return
     */
    @Override
    public List<VideoBanner> listBanner() {

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{

                List<VideoBanner> list = videoMapper.listBanner();
                log.info("从数据库里面找轮播图列表");
                return list;
            });

            if (cacheObj instanceof List){
                return  (List<VideoBanner>)cacheObj;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Video> queryVideoList() {

        try{

            Object cacheObj =  baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{

                List<Video> videoList = videoMapper.queryVideoList();

                return videoList;

            });

            if(cacheObj instanceof List){

                return  (List<Video>)cacheObj;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //可以返回兜底数据，业务系统降级
        return null;

    }


    @Override
    public Video queryDetailById(Integer videoId) {

        //单独构建一个缓存key，每个视频的key是不一样的
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL,videoId);

        try{

            Object cacheObject = baseCache.getOneHourCache().get( videoCacheKey, ()->{

                // 需要使用mybaits关联复杂查询
                Video video = videoMapper.queryDetailById(videoId);

                return video;

            });

            if(cacheObject instanceof Video){

                return  (Video)cacheObject;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(Video video) {
        int rows = videoMapper.save(video);
        System.out.println("保存对象的id= "+video.getId());

        return rows;
    }
}
