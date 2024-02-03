package org.hsiaomartin.springbootmall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.constant.ProductCategory;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;
}
