package com.xyx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class StoreApplicationTests {
    @Resource
    private DataSource dataSource;
    @Test
    void contextLoads() throws Exception{
        System.out.println(dataSource.getConnection());
    }


}
