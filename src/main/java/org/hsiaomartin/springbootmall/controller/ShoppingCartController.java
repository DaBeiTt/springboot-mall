package org.hsiaomartin.springbootmall.controller;

import org.hsiaomartin.springbootmall.dto.BuyItem;
import org.hsiaomartin.springbootmall.dto.CartItem;
import org.hsiaomartin.springbootmall.dto.CreateOrderRequest;
import org.hsiaomartin.springbootmall.service.ProductService;
import org.hsiaomartin.springbootmall.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(names = {"userLogin"})
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CreateOrderRequest createOrderRequest;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String getCart(Model model) {

        model.addAttribute("shoppingCart", shoppingCartService.getCart());
        model.addAttribute("createOrderRequest", createOrderRequest);

        return "order/cart";
    }

    @PostMapping("cart/add")
    public String addCartItem(@ModelAttribute BuyItem buyItem) {

        CartItem cartItem = new CartItem();
        cartItem.setProduct(productService.getProductById(buyItem.getProductId()));
        cartItem.setQuantity(buyItem.getQuantity());

        shoppingCartService.addCartItem(cartItem);

        BuyItem buyItemToList = new BuyItem();
        buyItemToList.setProductId(buyItem.getProductId());
        buyItemToList.setQuantity(buyItem.getQuantity());

        createOrderRequest.getBuyItemList().add(buyItemToList);

        return "redirect:/cart";
    }

    @RequestMapping("cart/remove/{productId}")
    public String removeCartItem(@PathVariable Integer productId) {

        shoppingCartService.removeCartItem(productId);

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            if(buyItem.getProductId() == productId) {
                createOrderRequest.getBuyItemList().remove(buyItem);
                break;
            }
        }

        return "redirect:/cart";
    }
}
