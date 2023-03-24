package com.xyx.mapper;

import com.xyx.entity.Order;
import com.xyx.entity.OrderItem;

public interface OrderMapper {
    /**
     * 增加订单
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 增加订单项
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);
}


