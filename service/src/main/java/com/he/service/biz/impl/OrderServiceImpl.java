package com.he.service.biz.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.he.dao.mapper.biz.OrderMapper;
import com.he.model.entity.biz.Order;
import com.he.service.biz.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int relDelById(Long id) {
        return orderMapper.relDelById(id);
    }

    @Override
    public int relDelAll() {
        return orderMapper.relDelAll();
    }
}
