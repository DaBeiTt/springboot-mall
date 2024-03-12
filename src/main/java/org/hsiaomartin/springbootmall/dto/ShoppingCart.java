package org.hsiaomartin.springbootmall.dto;

import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.model.Product;
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

        return this.cartItemList;
    }

    public Integer isExist(Product product) {

        Integer readyItemId =  product.getProductId();

        for(int i = 0;i<cartItemList.size();i++) {
            if(readyItemId == cartItemList.get(i).getProduct().getProductId()) {
                return i;
            }
        }

        return -1;
    }

    public Integer getTotal() {

        Integer total = 0;
        for(CartItem cartItem : cartItemList) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        return total;
    }
}
