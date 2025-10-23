package com.rummikub.demo.services;

import java.util.List;

import com.rummikub.demo.entities.Product;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getProductsByUser(Long userId);
    List<Product> getAlerts(Long userId); // Products near expiration
}

// "alertA" dE produtoS q tãO quasE vencendO