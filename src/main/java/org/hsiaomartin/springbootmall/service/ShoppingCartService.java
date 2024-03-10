package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public interface ShoppingCartService {

    void getCart(Model model);

    void addCartItem(CartItem cartItem);

    void removeCartItem(Integer productId);
}
