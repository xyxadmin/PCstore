package com.xyx;

import com.xyx.entity.Address;
import com.xyx.entity.Product;
import com.xyx.mapper.AddressMapper;
import com.xyx.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Resource
    private ProductMapper productMapper;
    @Test
    public void testInsert(){
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            System.out.println(product);
        }
    }


}
