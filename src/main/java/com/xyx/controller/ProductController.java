package com.xyx.controller;

import com.xyx.entity.Product;
import com.xyx.service.ProductService;
import com.xyx.utils.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/web/product")
public class ProductController extends HandlerController{
    @Resource
    private ProductService productService;

    @RequestMapping("/hotList")
    public JsonResult<List<Product>> hotList(){
        List<Product> hostList = productService.findHostList();
        return new JsonResult<>(OK,hostList);
    }
    @RequestMapping("/{id}/detail")
    public JsonResult<Product> findProduct(@PathVariable("id") Integer id){
        Product data = productService.findById(id);
        return new JsonResult<>(OK,data);
    }
}
