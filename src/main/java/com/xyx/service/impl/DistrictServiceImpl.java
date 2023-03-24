package com.xyx.service.impl;

import com.xyx.entity.District;
import com.xyx.mapper.DistrictMapper;
import com.xyx.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> parents = districtMapper.findByParent(parent);
        for(District result : parents){
            result.setId(null);
            result.setParent(null);
        }
        return parents;
    }

    @Override
    public String getNameByName(String code) {
        return districtMapper.findNameByCode(code);
    }
}
