package org.hsiaomartin.springbootmall.service.impl;

import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.service.ShoppingCartService;
import org.hsiaomartin.springbootmall.dto.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCart shoppingCart;

    @Override
    public ShoppingCart getCart() {

        return this.shoppingCart;
    }

    @Override
    public void addCartItem(CartItem cartItem) {

        if(cartItem.getQuantity() > cartItem.getProduct().getStock()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "庫存不足! 新增失敗");
        }

        // 判斷該 Product 是否已存在
        Integer index = shoppingCart.isExist(cartItem.getProduct());

        if(index == -1) {

            shoppingCart.addItem(cartItem);
        }
        else{
            CartItem existCartItem = shoppingCart.getResults().get(index);
            if (existCartItem.getQuantity() + cartItem.getQuantity() > cartItem.getProduct().getStock()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "庫存不足! 新增失敗");
            }
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
        }
    }

    @Override
    public void removeCartItem(Integer productId) {

        for(CartItem cartItem : shoppingCart.getResults()) {

            if(cartItem.getProduct().getProductId() == productId) {
                shoppingCart.removeItem(cartItem);
                break;
            }
        }
    }
}
