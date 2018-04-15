package com.he.service.biz;

import com.he.model.entity.biz.Order;
import com.he.service.ISuperService;

public interface IOrderService extends ISuperService<Order>{
    int addOrder(Order order);
}
