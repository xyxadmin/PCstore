package com.xyx.service.impl;

import com.xyx.entity.Address;
import com.xyx.mapper.AddressMapper;
import com.xyx.service.AddressService;
import com.xyx.service.DistrictService;
import com.xyx.service.exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Resource
    private AddressMapper addressMapper;
    @Resource
    private DistrictService districtService;


    @Override
    public void addAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countAddressByUid(uid);
        if(count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        //对address中的数据进行补全，省市区
        String provinceName = districtService.getNameByName(address.getProvinceCode());
        String cityName = districtService.getNameByName(address.getCityCode());
        String areaName = districtService.getNameByName(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 :0;
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedUser(username);
        Integer integer = addressMapper.insertAddress(address);
        if(integer != 1){
            throw new InsertException("插入用户收货地址数据时产生异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> addresses = addressMapper.findByUid(uid);
        for (Address address : addresses) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return addresses;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw new AddressNotFoundException("用户收货地址不存在");
        }
        //检测当前获取到的收货地址数据的归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        //先将所有收货地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows < 1){
            throw new UpdateException("更新数据时产生异常");
        }
        //将用户选中的地址设置为默认地址
        Integer result = addressMapper.updateDefaultByAid(aid, username, new Date());
        if(result != 1){
            throw new UpdateException("更新数据时产生异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        //判断有没有数据
        Address address = addressMapper.findByAid(aid);
        if(address == null){
            throw  new AddressNotFoundException("收货地址数据不存在");
        }
        if(address.getUid() != uid){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows != 1){
            throw new DeleteException("删除数据产生未知异常");
        }
        Integer count = addressMapper.countAddressByUid(uid);
        if(count == 0){
            return;
        }
        //将这条数据中的is_default设置为1
        Address lastModified = addressMapper.findLastModified(uid);
        if(address.getIsDefault() == 0){
            return;
        }
        Integer integer = addressMapper.updateDefaultByAid(lastModified.getAid(), username, new Date());
        if(integer != 1){
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if(result == null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }
        if(result.getUid() != uid){
            throw new AccessDeniedException("非法数据访问");
        }

        return result;
    }
}
