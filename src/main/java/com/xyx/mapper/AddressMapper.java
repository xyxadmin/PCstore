package com.xyx.mapper;

import com.xyx.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    //插入用户收货地址
    Integer insertAddress(Address address);
    //根据用户id查询用户收货地址总数
    Integer  countAddressByUid(@Param("uid") Integer uid);
    List<Address> findByUid(Integer uid);
    //收货地址默认
    Address findByAid(Integer aid);
    //将用户的收货地址设置为非默认
    Integer updateNonDefault(Integer uid);
    Integer updateDefaultByAid(@Param("aid") Integer adi,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    //删除收货地址
    Integer deleteByAid(Integer aid);
    Address findLastModified(Integer uid);
}


