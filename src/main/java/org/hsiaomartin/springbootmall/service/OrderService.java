package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;
import org.hsiaomartin.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
