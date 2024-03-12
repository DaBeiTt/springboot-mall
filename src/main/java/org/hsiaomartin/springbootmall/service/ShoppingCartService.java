package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.dto.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public interface ShoppingCartService {

    ShoppingCart getCart();

    void addCartItem(CartItem cartItem);

    void removeCartItem(Integer productId);
}
