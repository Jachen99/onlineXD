package com.example.onlinexd.config;

import com.example.onlinexd.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 配置拦截器
 *
 * 不用授权可以访问url  /api/v1/pub
 * 需要授权才能登录url  /api/v1/pri
 *
 * @author JaChen
 * @date 2023/1/15 15:06
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry
                .addInterceptor(loginInterceptor())
                .addPathPatterns("/api/v1/pri/*/*/**")  // 拦截全部路径
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");  // 不拦截哪个路径
        WebMvcConfigurer.super.addInterceptors(registry);

    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
//                .allowCredentials(true);
//    }
}
