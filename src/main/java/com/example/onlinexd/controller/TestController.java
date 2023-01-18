package com.example.onlinexd.controller;

import com.example.onlinexd.config.WeChatConfig;
import com.example.onlinexd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author JaChen
 * @date 2022/11/29 22:56
 */
@CrossOrigin  // 开启跨域
@RestController
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * 测试获取微信配置信息
     * @return
     */
    @RequestMapping("test_config")
    public JsonData toConfig(){

        return JsonData.buildSuccess(weChatConfig.toString());

    }

}
