package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.constant.ProductCategory;
import org.hsiaomartin.springbootmall.dto.ProductRequest;
import org.hsiaomartin.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
