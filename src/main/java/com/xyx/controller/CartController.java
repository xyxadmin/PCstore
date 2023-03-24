package com.xyx.controller;

import com.xyx.service.CartService;
import com.xyx.utils.JsonResult;
import com.xyx.vo.CartVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/web/cart")
public class CartController extends HandlerController{
    @Resource
    private CartService cartService;


    @RequestMapping("/add")
    public JsonResult<Void> addCart(Integer pid,
                                    Integer amount,
                                    HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid,pid,amount,username);
        return new JsonResult<>(OK);


    }
    @RequestMapping("")
    public JsonResult<List<CartVo>> getCartList(HttpSession session){
        List<CartVo> cartVos = cartService.showCartList(getUidFromSession(session));
        return new JsonResult<>(OK,cartVos);
    }

    @RequestMapping("/{cid}/num/add")
    public JsonResult<Integer> addCart(@PathVariable("cid") Integer cid,
                                       HttpSession session){
        Integer data = cartService.addNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK,data);

    }

    @RequestMapping("/list")
    public JsonResult<List<CartVo>> getCartListByCid(Integer[] cids,HttpSession session){
        List<CartVo> cartVos = cartService.showCartListByCid(getUidFromSession(session),
                cids);
        return new JsonResult<>(OK,cartVos);
    }

}
