package com.xyx.service;

import com.xyx.entity.Address;

import java.util.List;

public interface AddressService {
    //添加收货地址
    void addAddress(Integer uid,String username,Address address);
    List<Address> getByUid(Integer uid);
    //设置默认地址
    void setDefault(Integer aid,Integer uid,String username);
    //删除收货地址
    void deleteAddress(Integer aid,Integer uid,String username);
    //根据aid获得地址
    Address getByAid(Integer aid,Integer uid);
}
