package com.xyx;

import com.xyx.entity.Address;
import com.xyx.mapper.AddressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Resource
    private AddressMapper addressMapper;
    @Test
    public void testInsert(){
        Address address = new Address("管理员", new Date(), "管理员",
                new Date(), null, 17, "hello", "河南省",
                "新乡市", "小屯村", "333", "新乡",
                "1001", "999", "china", "9999", "9999",
                "0", 0);
        addressMapper.insertAddress(address);
    }
    @Test
    public void testCount(){
        Integer integer = addressMapper.countAddressByUid(17);
        System.out.println("==================="+integer+"======================");
    }
    @Test
    public void testFindByUid(){
        List<Address> byUid = addressMapper.findByUid(17);
        for (Address address : byUid) {
            System.out.println(address);
        }
    }
    @Test
    public void testFindByAid(){
        Address address = addressMapper.findByAid(6);
        System.out.println(address);
    }
    @Test
    public void testUpdateNonDefault(){
        addressMapper.updateNonDefault(16);
    }

    @Test
    public void testUpdateDefaultByAid(){
        addressMapper.updateDefaultByAid(6, "管理员", new Date());
    }
    @Test
    public void testDelete(){
        addressMapper.deleteByAid(3);
    }

    @Test
    public void testFindLastModified(){
        System.out.println(addressMapper.findLastModified(17));
    }


}
