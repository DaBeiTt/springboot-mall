package org.hsiaomartin.springbootmall.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;
import org.hsiaomartin.springbootmall.dto.OrderQueryParams;
import org.hsiaomartin.springbootmall.dto.ShoppingCart;
import org.hsiaomartin.springbootmall.util.SuccessObject;
import org.hsiaomartin.springbootmall.model.Order;
import org.hsiaomartin.springbootmall.model.OrderItem;
import org.hsiaomartin.springbootmall.service.OrderService;
import org.hsiaomartin.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Validated
@SessionAttributes("userLogin")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreateOrderRequest createOrderRequest;

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/users/{userId}/orders")
    public String getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0")  @Min(0) Integer offset,

            Model model
    ) {

        OrderQueryParams orderQueryParams = new OrderQueryParams(userId, limit, offset);

        // 取得 order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        // 取得 order 總數
        Integer count = orderService.countOrder(orderQueryParams);

        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        model.addAttribute("orderPage", page);

        return "order/userOrder";
    }

    @GetMapping("users/{userId}/orders/{orderId}")
    public String getOrderItemsByOrderId(@PathVariable Integer userId,
                                        @PathVariable Integer orderId,
                                         Model model) {

        Order order = orderService.getOrderById(orderId);

        List<OrderItem> orderItemList = order.getOrderItemList();

        model.addAttribute("orderItemList", orderItemList);

        return "order/detail";
    }

    @PostMapping("/users/{userId}/orders")
    public String createOrder(@PathVariable Integer userId,
                              @Valid CreateOrderRequest createOrderRequest,
                              Model model) {

        orderService.createOrder(userId, createOrderRequest);

        SuccessObject successObject = new SuccessObject("createOrder", "訂單新增成功!");
        model.addAttribute("success", successObject);

        // 訂單創建完成後將 createOrderRequest & shoppingCart 清空
        this.createOrderRequest.setBuyItemList(new ArrayList<>());
        this.shoppingCart.clearCart();

        return "message/success";
    }

    @DeleteMapping("/users/{userId}/orders/{orderId}")
    public String deleteOrderById(@PathVariable Integer userId,
                                  @PathVariable Integer orderId,
                                  Model model) {

        orderService.deleteOrderById(orderId);

        SuccessObject successObject = new SuccessObject("deleteOrder", "訂單刪除成功");
        model.addAttribute("success", successObject);

        return "message/success";
    }
}
