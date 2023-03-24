package com.xyx.mapper;

import com.xyx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    int insertUser(User user);
    User findUserByUserName(String username);
    //用户修改密码
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime
                                );
    User findByUid(Integer uid);
    //修改用户资料
    Integer updateUser(User user);
    //更新用户头像
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime
                             );

}
