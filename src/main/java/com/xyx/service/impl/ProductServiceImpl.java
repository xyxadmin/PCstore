package com.xyx.service.impl;

import com.xyx.entity.Product;
import com.xyx.mapper.ProductMapper;
import com.xyx.service.ProductService;
import com.xyx.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findHostList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product product : hotList) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return hotList;
    }

    @Override
    public Product findById(Integer id) {
        Product result = productMapper.findById(id);
        if(result == null){
            throw new ProductNotFoundException("商品不存在");
        }
        return result;
    }
}
