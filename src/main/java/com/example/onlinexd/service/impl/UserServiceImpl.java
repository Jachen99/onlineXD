package com.example.onlinexd.service.impl;

import com.example.onlinexd.mapper.UserMapper;
import com.example.onlinexd.model.entity.User;
import com.example.onlinexd.service.UserService;
import com.example.onlinexd.utils.CommonUtils;
import com.example.onlinexd.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

/**
 * @author JaChen
 * @date 2023/1/15 12:28
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User userById(Integer uid) {

        return userMapper.userById(uid);

    }

    @Override
    public int save(User user) {

        User u = parseToUser(user);
        if( u != null){
            return userMapper.save(u);
        }else {
            return -1;
        }

    }


    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {

        User user = userMapper.findByPhoneAndPwd(phone, CommonUtils.MD5(pwd));

        if(user == null){
            return null;
        }else {
            return JwtUtils.geneJsonWebToken(user);
        }

    }

    /**
     * 解析 user 对象
     * @param user
     * @return
     */
    private User parseToUser(User user) {

        if(user.getPhone()!=null&&user.getPwd()!=null){

            // builder建造者模式

            User build = User.builder()
                    .name(user.getName())
                    .headImg(getRandomImg())
                    .createTime(new Date())
                    .phone(user.getPhone())
                    .pwd(CommonUtils.MD5(user.getPwd())).build();

            return build;

        }else {
            return null;
        }

    }

    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

}
