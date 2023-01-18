package com.example.onlinexd.interceptor;

import com.example.onlinexd.utils.JsonData;
import com.example.onlinexd.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 拦截器
 *
 * @author JaChen
 * @date 2023/1/15 14:29
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入到controller之前的方法
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String token = request.getHeader("token");
            if (token == null){
                token = request.getParameter("token");
            }
            if (StringUtils.isNotBlank(token)){
                Claims jwt = JwtUtils.checkJWT(token);
                if (jwt == null){
                    // 登录失败
                    sendMessage(response, JsonData.buildError("登录超时，请重新登录！"));
                    return false;
                }
                Integer id = (Integer) jwt.get("id");
                String name = (String) jwt.get("name");
                request.setAttribute("user_id",id);
                request.setAttribute("name",name);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sendMessage(response, JsonData.buildError("登录超时，请重新登录！"));
        return false;
    }


    /**
     * 在service层把json信息响应给前端
     * @param response
     * @param o
     */
    public static void sendMessage(HttpServletResponse response,Object o){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(o));
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
