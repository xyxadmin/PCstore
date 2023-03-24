package com.xyx.service.impl;

import com.xyx.entity.Cart;
import com.xyx.entity.Product;
import com.xyx.mapper.CartMapper;
import com.xyx.mapper.ProductMapper;
import com.xyx.service.CartService;
import com.xyx.service.exception.AccessDeniedException;
import com.xyx.service.exception.CartNotFounException;
import com.xyx.service.exception.InsertException;
import com.xyx.service.exception.UpdateException;
import com.xyx.vo.CartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    
    @Resource
    private ProductMapper productMapper;
    @Override
    public void addToCart(Integer uid, Integer pid, 
                          Integer amount, String username) {
        Date date = new Date();
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if(result == null){
            Long price = productMapper.findById(pid).getPrice();
            Cart cart = new Cart(null,uid,pid,price,amount);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            Integer rows = cartMapper.insert(cart);
            if(rows != 1){
                throw new InsertException("插入数据时产生未知的异常");
            }
        }else{
            Integer num = result.getNum()+amount;
            Integer cid = result.getCid();
            Integer rows = cartMapper.updateNumByCid(cid, num, username, date);
            if(rows != 1){
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }

    }

    @Override
    public List<CartVo> showCartList(Integer uid) {
        return cartMapper.showCartList(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {

        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFounException("数据不存在");
        }
        if(result.getUid() != uid){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVo> showCartListByCid(Integer uid, Integer[] cids) {
        List<CartVo> cartVos = cartMapper.showCartListByCid(cids);
        Iterator<CartVo> iterator = cartVos.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getUid() != uid){
                cartVos.remove(iterator.next());
            }
        }
        return cartVos;
    }
}
