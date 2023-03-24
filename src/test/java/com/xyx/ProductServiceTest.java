package com.xyx;

import com.xyx.entity.Product;
import com.xyx.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Resource
    private ProductService productService;
    @Test
    public void testInsert(){
        List<Product> hostList = productService.findHostList();
        for (Product product : hostList) {
            System.out.println(product);
        }
    }


}
