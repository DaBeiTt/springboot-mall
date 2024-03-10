package org.hsiaomartin.springbootmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.model.Product;

@Getter
@Setter
public class CartItem {

    private Product product;
    private Integer quantity;
}
