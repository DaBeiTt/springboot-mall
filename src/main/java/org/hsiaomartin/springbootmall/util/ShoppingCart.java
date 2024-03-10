package org.hsiaomartin.springbootmall.util;

import org.hsiaomartin.springbootmall.dto.CartItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {

    private List<CartItem> cartItemList = new ArrayList<>();

    public void addItem(CartItem cartItem) {

        cartItemList.add(cartItem);
    }

    public void removeItem(CartItem cartItem) {

        cartItemList.remove(cartItem);
    }

    public List<CartItem> getResults() {

        return cartItemList;
    }

    public Integer getTotal() {

        Integer total = 0;
        for(CartItem cartItem : cartItemList) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return total;
    }
}
