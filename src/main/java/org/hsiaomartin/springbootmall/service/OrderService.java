package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;
import org.hsiaomartin.springbootmall.dto.OrderQueryParams;
import org.hsiaomartin.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
