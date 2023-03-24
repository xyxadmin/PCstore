package com.xyx.controller;

import com.xyx.entity.Address;
import com.xyx.service.AddressService;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/web/address")
public class AddressController extends HandlerController{
    @Resource
    private AddressService addressService;

    @RequestMapping("/addAddress")
    public JsonResult<Void> addAddress(Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping("")
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> addresses = addressService.getByUid(uid);
        return new JsonResult<>(OK,addresses);
    }

    @RequestMapping("/{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
                                       HttpSession session){
        addressService.setDefault(aid,getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);

    }
    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,
                                          HttpSession session){
        addressService.deleteAddress(aid, getUidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
