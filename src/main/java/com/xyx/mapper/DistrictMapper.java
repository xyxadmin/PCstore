package com.xyx.mapper;

import com.xyx.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据用户的父代号查询区域信息
     * @param parent 父代号
     * @return 某个父区域的所有区域列表
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
