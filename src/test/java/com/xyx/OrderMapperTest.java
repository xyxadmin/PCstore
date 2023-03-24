package com.xyx;

import com.xyx.entity.Order;
import com.xyx.entity.OrderItem;
import com.xyx.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Resource
    private OrderMapper orderMapper;

    @Test
    public void testInsertOrder(){
        Order order = new Order("root",new Date(),"小明",new Date(),null,16,"peter","888",
                "甘肃省","兰州市","小区","西北大学",77L,1,new Date(),new Date());

        orderMapper.insertOrder(order);
    }

    @Test
    public void testInsertOrderItem(){
        OrderItem orderItem = new OrderItem("root",new Date(),"root",new Date(),
                null,1,10010,"笔记本电脑","/test/",999L,10);
        orderMapper.insertOrderItem(orderItem);
    }

}
