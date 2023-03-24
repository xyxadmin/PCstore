package com.xyx;

import com.xyx.entity.District;
import com.xyx.service.DistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {
    @Resource
    private DistrictService districtService;

    @Test
    public void testService(){
        List<District> districtList = districtService.getByParent("86");
        for (District district : districtList) {
            System.out.println(district);
        }
    }

}
