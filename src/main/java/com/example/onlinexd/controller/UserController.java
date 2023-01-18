package com.example.onlinexd.controller;

import com.example.onlinexd.model.entity.User;
import com.example.onlinexd.model.request.LoginRequest;
import com.example.onlinexd.service.UserService;
import com.example.onlinexd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author JaChen
 * @date 2022/12/6 23:55
 */
@CrossOrigin  // 开启跨域
@RestController
@RequestMapping("/api/v1/pri/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 根据token中的user_id查询用户信息
     * @param request
     * @return
     */
    @GetMapping("user_by_token")
    public JsonData UserByToken(HttpServletRequest request){


        System.out.println("request = " + request);

        Integer uid = (Integer) request.getAttribute("user_id");

        if (uid == null){
            return JsonData.buildError("查询失败！");
        }

        User user = userService.userById(uid);

        return JsonData.buildSuccess(user);

    }


    /**
     * 用户登录的接口
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService
                .findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return token == null
                ?JsonData.buildError("登录失败，账号密码错误"): JsonData.buildSuccess(token);

    }

    /**
     * 用户注册接口
     * @param user
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody User user){

        int rows = userService.save(user);

        return rows == 1
                ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");

    }


}
