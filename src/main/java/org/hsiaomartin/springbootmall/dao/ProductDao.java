package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.dto.ProductRequest;
import org.hsiaomartin.springbootmall.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);

    public Integer createProduct(ProductRequest productRequest);
}
