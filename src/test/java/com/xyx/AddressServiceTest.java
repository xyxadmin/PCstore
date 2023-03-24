package com.xyx;

import com.xyx.entity.Address;
import com.xyx.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Resource
    private AddressService addressService;
    @Test
    public void testAddAddress(){
        Address address = new Address("root", new Date(), "管理员", new Date(), null, 16, "test", "河北省", "邯郸", "石家庄", "888", "", "888", "10110", "china", "1111", "9999", "0", null);
        addressService.addAddress(16,"root",address);
    }
    @Test
    public void testSetDefault(){
        addressService.setDefault(5, 17, "管理员");
    }
    @Test
    public void testDelete(){
        addressService.deleteAddress(4, 17, "管理员");
    }
}
