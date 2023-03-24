package com.xyx.mapper;

import com.xyx.entity.Cart;
import com.xyx.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    //添加购物车
    Integer insert(Cart cart);
    Integer updateNumByCid(@Param("cid") Integer cid,
                   @Param("num") Integer num,
                   @Param("modifiedUser") String modifiedUser,
                   @Param("modifiedTime") Date modifiedTime);
    Cart findByUidAndPid(@Param("uid") Integer uid,
                         @Param("pid") Integer pid);


    //显示购物车列表
    List<CartVo> showCartList(Integer uid);
    //根据cid判断数据库中是否有数据
    Cart findByCid(Integer cid);
    List<CartVo> showCartListByCid(Integer[] cids);

}
