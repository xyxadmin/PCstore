package com.xyx.service;

import com.xyx.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHostList();
    Product findById(Integer id);
}
