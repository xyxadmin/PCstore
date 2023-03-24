package com.xyx;

import com.xyx.entity.Cart;
import com.xyx.mapper.CartMapper;
import com.xyx.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Resource
    private CartMapper cartMapper;
    @Test
    public void testInsert(){
        Cart cart = new Cart(null,16,1005,778L,10);
        cartMapper.insert(cart);
    }
    @Test
    public void testUpdateNumByCid(){
        cartMapper.updateNumByCid(1, 10, "root", new Date());
    }
    @Test
    public void testFindByUidAndPid(){
        Cart cart = cartMapper.findByUidAndPid(17, 1001);
        System.out.println(cart);
    }
    @Test
    public void testShowList(){
        List<CartVo> cartVos = cartMapper.showCartList(18);
        for (CartVo cartVo : cartVos) {
            System.out.println(cartVo);
        }
    }

    @Test
    public void testFindByCid(){
        System.out.println(cartMapper.findByCid(7));
    }

    @Test
    public void testShowListByCid(){
        Integer[] cids = {1,2,3,4,5,6,7};
        List<CartVo> cartVos = cartMapper.showCartListByCid(cids);
        System.out.println(cartVos);
    }


}
