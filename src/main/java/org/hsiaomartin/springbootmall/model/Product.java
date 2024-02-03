package org.hsiaomartin.springbootmall.model;

import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.constant.ProductCategory;

import java.util.Date;

@Getter
@Setter
public class Product {

    private Integer productId;
    private String productName;
    private ProductCategory category;
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}
