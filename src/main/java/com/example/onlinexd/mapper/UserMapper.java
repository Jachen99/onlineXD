package com.example.onlinexd.mapper;

import com.example.onlinexd.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author JaChen
 * @date 2023/1/15 12:43
 */
@Repository
public interface UserMapper {

    int save(User user);

    User findByPhone(@Param("phone") String phone);


    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User userById(Integer uid);
}
