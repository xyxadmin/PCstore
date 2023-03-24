package com.xyx.service;

import com.xyx.entity.Order;

public interface OrderService {
    Order create(Integer aid, Integer uid, String username, Integer[] cids);
}
