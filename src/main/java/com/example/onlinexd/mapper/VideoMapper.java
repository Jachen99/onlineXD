package com.example.onlinexd.mapper;

import com.example.onlinexd.model.entity.Video;
import com.example.onlinexd.model.entity.VideoBanner;
import com.example.onlinexd.model.entity.VideoOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * video数据访问层
 *
 * @author JaChen
 * @date 2022/11/29 22:43
 */
@Repository
public interface VideoMapper {

    /**
     * 查询video都有对象的方法
     * @return 返回video对象的list
     */
    List<Video> queryVideoList();


    /**
     * 获取主页轮播图列表
     * @return
     */
    List<VideoBanner> listBanner();


    /**
     * 获取Video的详情信息 包含章 集
     * TODO:需要mybatis关联复杂查询
     * @return  三表联查
     */
    Video queryDetailById(@Param("video_id") Integer videoId);


    /**
     * 更新指定id的视频
     * @param video
     * @return
     */
    @Update("UPDATE video SET title=#{title} WHERE id =#{id}")
    int update(Video video);


    /**
     * 删除指定id的视频
     * @param id
     * @return
     */
    @Delete("DELETE FROM video WHERE id =#{id}")
    int delete(int id);

    /**
     * 添加视频
     * @param video
     * @return
     */
    @Insert("INSERT INTO `video` ( `title`, `summary`, " +
            "`cover_img`, `price`, `create_time`,`point`)" +
            "VALUES" +
            "(#{title}, #{summary}, #{coverImg}, #{price}, #{creatTime}, #{point});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Video video);

}
