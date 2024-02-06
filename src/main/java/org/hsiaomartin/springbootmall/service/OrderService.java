package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
