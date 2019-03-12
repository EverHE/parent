package com.he.biz.impl;

import com.he.base.impl.SuperServiceImpl;
import com.he.mapper.biz.OrderMapper;
import com.he.entity.biz.Order;
import com.he.biz.IOrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends SuperServiceImpl<OrderMapper,Order> implements IOrderService {

    @Override
    public int addOrder(Order order) {
        return baseMapper.insert(order);
    }
}
