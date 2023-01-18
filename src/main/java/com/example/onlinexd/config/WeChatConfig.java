package com.example.onlinexd.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 *  TODO: 微信配置类
 *
 * @author JaChen
 * @date 2022/12/24 18:53
 */
@Data
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    /**
     * 公众号appid
     */
    @Value("${wxpay.appid}")
    private String appId;

    /**
     * 公主号密钥
     */
    @Value("${wxpay.appsecret}")
    private String appsecret;

}
