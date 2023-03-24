package com.xyx.mapper;

import com.xyx.entity.Product;

import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();
    Product findById(Integer id);
}
