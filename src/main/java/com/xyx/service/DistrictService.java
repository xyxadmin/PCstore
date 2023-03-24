package com.xyx.service;

import com.xyx.entity.District;

import java.util.List;

public interface DistrictService {
    List<District> getByParent(String parent);
    String getNameByName(String code);
}
