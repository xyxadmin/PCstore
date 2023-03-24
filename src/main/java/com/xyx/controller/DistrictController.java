package com.xyx.controller;

import com.xyx.entity.District;
import com.xyx.service.DistrictService;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/web")
public class DistrictController extends HandlerController{
    @Resource
    private DistrictService districtService;

    @RequestMapping("/district")
    public JsonResult<List> getParent(String parent){
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<>(OK,list);
    }

}
