package com.xyx;

import com.xyx.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {
    @Resource
    private CartService cartService;
    @Test
    public void testInsert(){
        cartService.addToCart(18, 10000006, 5, "管理员");
    }
    @Test
    public void testUpdate(){
        cartService.addToCart(17, 10000006, 10, "李玉");
    }
    @Test
    public void testUpdateCart(){
        cartService.addNum(7, 18, "root");
    }



}
