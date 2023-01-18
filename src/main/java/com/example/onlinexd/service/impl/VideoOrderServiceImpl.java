package com.example.onlinexd.service.impl;

import com.example.onlinexd.exception.XDException;
import com.example.onlinexd.mapper.EpisodeMapper;
import com.example.onlinexd.mapper.PlayRecordMapper;
import com.example.onlinexd.mapper.VideoMapper;
import com.example.onlinexd.mapper.VideoOrderMapper;
import com.example.onlinexd.model.entity.Episode;
import com.example.onlinexd.model.entity.PlayRecord;
import com.example.onlinexd.model.entity.Video;
import com.example.onlinexd.model.entity.VideoOrder;
import com.example.onlinexd.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author JaChen
 * @date 2023/1/15 20:44
 */
@Service
@Transactional
public class VideoOrderServiceImpl implements VideoOrderService {


    @Resource
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Resource
    private EpisodeMapper episodeMapper;

    @Resource
    private PlayRecordMapper playRecordMapper;


    @Override
    public List<VideoOrder> listOrderById(Integer userId) {
        return videoOrderMapper.listOrderById(userId);
    }

    /**
     * 下单操作
     * 未来版本：优惠券抵扣，风控用户检查，生成订单基础信息，生成支付信息
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Transactional
    @Override
    public int save(int userId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if(videoOrder!=null){return  0;}

        Video video = videoMapper.queryDetailById(videoId);
        if(video == null){
            throw  new XDException(-1,"没有该视频信息，请运营人员检查");
        }

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState("1");
        newVideoOrder.setTotalFree(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

        //生成播放记录
        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw new XDException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }


}

