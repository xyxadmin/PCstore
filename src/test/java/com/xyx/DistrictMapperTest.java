package com.xyx;

import com.xyx.entity.District;
import com.xyx.mapper.DistrictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Resource
    private DistrictMapper districtMapper;
    @Test
    public void testFind(){
        List<District> parents = districtMapper.findByParent("110100");
        for(District parent : parents){
            System.out.println(parent);
        }
    }
    @Test
    public void testFindName(){
        String nameByCode = districtMapper.findNameByCode("610000");
        System.out.println("========="+nameByCode+"=============");
    }
   
}
