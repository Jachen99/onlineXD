package com.example.onlinexd.controller;

import com.example.onlinexd.model.entity.VideoOrder;
import com.example.onlinexd.model.request.VideoOrderRequest;
import com.example.onlinexd.service.VideoOrderService;
import com.example.onlinexd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/1/15 20:17
 */
@CrossOrigin  // 开启跨域
@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Resource
    private VideoOrderService videoOrderService;


    /**
     * 获取订单列表
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderById(userId);

        return JsonData.buildSuccess(videoOrderList);

    }


    /**
     * 下单接口
     * @return
     */
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody(required=false) VideoOrderRequest videoOrderRequest, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());

        return rows == 0 ? JsonData.buildError("下单失败"):JsonData.buildSuccess();
    }

}
