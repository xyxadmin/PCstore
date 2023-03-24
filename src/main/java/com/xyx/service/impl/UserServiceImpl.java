package com.xyx.service.impl;

import com.xyx.entity.User;
import com.xyx.mapper.UserMapper;
import com.xyx.service.UserService;
import com.xyx.service.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    //声明MD5算法
    private String getMD5Password(String password,String salt){
        for(int i = 0;i < 3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public int addUser(User user) {
        //判断用户是否注册过
        String username = user.getUsername();
        User name = userMapper.findUserByUserName(username);
        if(name != null){
            throw new UsernameDuplicatedException("用户名:"+username+"已注册");
        }
        //密码加密
        //串+密码+串--》MD5连续加载3次
        //盐值+密码+盐值 ——》MD5
        String password = user.getPassword();
        //获取盐值(随机生成)
        String salt = UUID.randomUUID().toString().toUpperCase();
        //盐值记录
        user.setSalt(salt);
        //将密码和盐值作为整体进行加密处理
        String md5Password = getMD5Password(password, salt);
        user.setPassword(md5Password);
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册业务
        int result = userMapper.insertUser(user);
        if(result != 1){
            throw new InsertException("注册过程产生了未知异常");
        }

        return result;
    }

    @Override
    public User findUser(String username) {
        User user = userMapper.findUserByUserName(username);
        if(user == null){
            throw new UserNotFoundException("用户名不存在");
        }

        return user;
    }


    @Override
    public User login(String username, String password) {
        User user = userMapper.findUserByUserName(username);
        if(user == null){
            throw new UserNotFoundException("用户数据不存在");
        }
        //检查密码
        //获取数据库中的密码
        String userPassword = user.getPassword();
        String salt = user.getSalt();
        //当前输入密码
        String md5Password = getMD5Password(password, salt);
        //比较密码
        if(!userPassword.equals(md5Password)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断数据库中数据是否删除
        Integer isDelete = user.getIsDelete();
        if(isDelete == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //登录成功，返回用户数据,封装新用户，只保留有用的数据
        //这样做，减少了返回数据的体量，提高了数据返回速度
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());
        return user1;
    }

    @Override
    public void changePassword(Integer uid, String username,
                               String oldPassword, String newPassword) {

        User byUid = userMapper.findByUid(uid);
        if(byUid == null || byUid.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码与数据库中的密码进行比较
        String salt = byUid.getSalt();
        String md5Password = getMD5Password(oldPassword, salt);
        if(!md5Password.equals(byUid.getPassword())){
            throw new PasswordNotMatchException("原始密码错误");
        }
        //将新密码设置到数据库中
        //将新密码进行加密
        String newMD5Password = getMD5Password(newPassword, byUid.getSalt());
        Integer result = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if(result != 1){
            throw new UpdateException("更新时数据异常");
        }

    }

    @Override
    public void updateUser(User user, Integer uid, String username) {
        User userByUid = getUserByUid(uid);
        if(userByUid == null || userByUid.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer integer = userMapper.updateUser(user);
        if(integer != 1){
            throw new UpdateException("更新数据时发生未知异常");
        }
    }


    @Override
    public User getUserByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }

        return result;
    }

    @Override
    public void updateAvatar(Integer uid, String avatar, String modifiedUser) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());
        if(rows != 1){
            throw new UpdateException("更新时用户头像异常");
        }
    }
}
