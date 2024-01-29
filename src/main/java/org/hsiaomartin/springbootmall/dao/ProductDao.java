package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.dto.ProductRequest;
import org.hsiaomartin.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
