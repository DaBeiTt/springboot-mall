package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.dto.BuyItem;
import org.hsiaomartin.springbootmall.service.ProductService;
import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.util.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userLogin")
public class ShoppingCartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ProductService productService;

    @GetMapping("cart")
    public String getCart(Model model) {

        model.addAttribute("shoppingCart", shoppingCart);

        return "order/cart";
    }

    @PostMapping("cart/add")
    public String addCartItem(@ModelAttribute BuyItem buyItem) {

        CartItem cartItem = new CartItem();
        cartItem.setProduct(productService.getProductById(buyItem.getProductId()));
        cartItem.setQuantity(buyItem.getQuantity());
        shoppingCart.addItem(cartItem);

        return "redirect:/cart";
    }

    @RequestMapping("cart/remove/{productId}")
    public String removeCartItem(@PathVariable Integer productId) {

        for(CartItem cartItem : shoppingCart.getResults()) {

            if(cartItem.getProduct().getProductId() == productId) {
                shoppingCart.removeItem(cartItem);
                break;
            }
        }

        return "redirect:/cart";
    }
}
