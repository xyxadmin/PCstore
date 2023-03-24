package com.xyx;

import com.xyx.entity.User;
import com.xyx.mapper.UserMapper;
import com.xyx.service.UserService;
import com.xyx.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Test
    public void add(){
        try{
            User user = new User();
            user.setUsername("test");
            user.setPassword("123");
            int result = userService.addUser(user);
            System.out.println("========="+result+"==========");
        }catch (ServiceException e){
            e.printStackTrace();
        }
    }
    @Test
    public void find(){
        User user = userService.findUser("李天");
        System.out.println(user);
    }
    @Test
    public void login(){
        User user = userService.login("李天", "999");
        System.out.println(user);
    }

    @Test
    public void datePassword(){
        Integer ff = userMapper.updatePasswordByUid(16, "123", "ff", new Date());
        System.out.println("================="+ff+"==================");
    }

    @Test
    public void findByUid(){
        User user = userMapper.findByUid(16);
        System.out.println("================="+user+"==================");
    }
    @Test
    public void changePassword(){
        userService.changePassword(17, "李天", "999", "123");
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setUid(18);
        user.setPhone("88888");
        user.setEmail("88888@qq.com");
        user.setGender(1);
        user.setModifiedUser("test");
        user.setModifiedTime(new Date());
        userMapper.updateUser(user);
    }

    @Test
    public void updateService(){
        User user = new User();
        user.setPhone("777");
        user.setEmail("777@qq.com");
        user.setGender(1);
        userService.updateUser(user,3,"风清扬");
    }
    @Test
    public void updateAvatar(){
        userMapper.updateAvatarByUid(18, "abcdefg", "test", new Date());
    }

    @Test
    public void updateAvatarService(){
        userService.updateAvatar(17, "d://mybatis", "管理员");
    }
}
