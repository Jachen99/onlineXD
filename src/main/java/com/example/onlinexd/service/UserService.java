package com.example.onlinexd.service;

import com.example.onlinexd.model.entity.User;

import java.util.Map;

/**
 * @author JaChen
 * @date 2023/1/15 12:27
 */
public interface UserService {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(User user);


    String findByPhoneAndPwd(String phone, String pwd);

    User userById(Integer uid);
}
