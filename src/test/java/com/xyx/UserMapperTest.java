package com.xyx;

import com.xyx.entity.User;
import com.xyx.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void testFind(){
        System.out.println(userMapper.findUserByUserName("张三"));
    }
    @Test
    public void testInsert(){
        User user = new User(null, "王明", "123", "123", "123", "123", 1, "123", 1);

    }
}
