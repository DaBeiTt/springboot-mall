package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.ProductRequest;
import org.hsiaomartin.springbootmall.model.Product;

public interface ProductService {

    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);
}
