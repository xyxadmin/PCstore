package com.xyx.service;

import com.xyx.entity.User;

public interface UserService {
    int addUser(User user);
    User findUser(String username);
    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword
                        );
    //修改用户资料
    void updateUser(User user,Integer uid,String username);
    User getUserByUid(Integer uid);//辅助
    //修改用户头像
    void updateAvatar(Integer uid,String avatar,String modifiedUser);
}
