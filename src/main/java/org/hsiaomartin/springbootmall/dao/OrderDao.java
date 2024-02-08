package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.dto.OrderQueryParams;
import org.hsiaomartin.springbootmall.model.Order;
import org.hsiaomartin.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
