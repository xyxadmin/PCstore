package com.xyx;

import com.xyx.entity.Address;
import com.xyx.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Resource
    private OrderService orderService;
    @Test
    public void testCreate(){
        Integer[] cids = {4,5,6,7,9};
        orderService.create(9, 18, "管理员", cids);
    }

}
