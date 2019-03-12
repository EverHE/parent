package com.he.biz;

import com.he.base.ISuperService;
import com.he.entity.biz.Order;

public interface IOrderService extends ISuperService<Order> {
    int addOrder(Order order);
}
