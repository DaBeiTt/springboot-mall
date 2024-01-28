package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.model.Product;

public interface ProductDao {

    public Product getProductById(Integer productId);
}
