package com.xyx.service;

import com.xyx.vo.CartVo;

import java.util.List;

public interface CartService {
    void addToCart(Integer uid,Integer pid,Integer amount,String username);
    List<CartVo> showCartList(Integer uid);
    //更新购物车数量
    Integer addNum(Integer cid,Integer uid,String username);
    List<CartVo> showCartListByCid(Integer uid,Integer[] cids);
}
