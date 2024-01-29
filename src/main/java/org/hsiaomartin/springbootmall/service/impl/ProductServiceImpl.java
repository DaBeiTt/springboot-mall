package org.hsiaomartin.springbootmall.service.impl;

import org.hsiaomartin.springbootmall.dao.ProductDao;
import org.hsiaomartin.springbootmall.dto.ProductRequest;
import org.hsiaomartin.springbootmall.model.Product;
import org.hsiaomartin.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }
}
