package com.xyx.controller;

import com.xyx.entity.Order;
import com.xyx.service.OrderService;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RequestMapping("/web/order")
@RestController
public class OrderController extends HandlerController{
    @Resource
    private OrderService  orderService;

    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session){

        Order data = orderService.create(aid, getUidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK,data);
    }

}
