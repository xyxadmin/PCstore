package com.xyx.service.impl;

import com.xyx.entity.Address;
import com.xyx.entity.Order;
import com.xyx.entity.OrderItem;
import com.xyx.mapper.OrderMapper;
import com.xyx.service.AddressService;
import com.xyx.service.CartService;
import com.xyx.service.OrderService;
import com.xyx.service.exception.InsertException;
import com.xyx.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressService addressService;

    @Resource
    private CartService cartService;
    @Override
    public Order create(Integer aid, Integer uid,
                          String username, Integer[] cids) {

        List<CartVo> cartVos = cartService.showCartListByCid(uid, cids);
        //计算商品总价
        Long totalPrice = 0L;
        for(CartVo cartVo : cartVos){
            totalPrice += cartVo.getRealPrice() * cartVo.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setOrderTime(new Date());
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setCreatedTime(new Date());
        order.setCreatedUser(username);
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);
        //查询数据
        Integer rows = orderMapper.insertOrder(order);
        if(rows != 1){
            throw new InsertException("插入数据错误");
        }
        for(CartVo cartVo : cartVos){
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            rows = orderMapper.insertOrderItem(orderItem);
            if(rows != 1){
                throw new InsertException("插入数据错误");
            }
        }
        return order;
    }
}
