package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.dto.BuyItem;
import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.service.ProductService;
import org.hsiaomartin.springbootmall.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userLogin")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @GetMapping("cart")
    public String getCart(Model model) {

        shoppingCartService.getCart(model);

        return "order/cart";
    }

    @PostMapping("cart/add")
    public String addCartItem(@ModelAttribute BuyItem buyItem) {

        CartItem cartItem = new CartItem();
        cartItem.setProduct(productService.getProductById(buyItem.getProductId()));
        cartItem.setQuantity(buyItem.getQuantity());

        shoppingCartService.addCartItem(cartItem);

        return "redirect:/cart";
    }

    @RequestMapping("cart/remove/{productId}")
    public String removeCartItem(@PathVariable Integer productId) {

        shoppingCartService.removeCartItem(productId);

        return "redirect:/cart";
    }
}
