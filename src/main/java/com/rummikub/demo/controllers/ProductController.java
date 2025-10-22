package com.rummikub.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rummikub.demo.entities.Product;
import com.rummikub.demo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUser(@PathVariable Long userId) {
        return productService.getProductsByUser(userId);
    }

    @GetMapping("/alerts/{userId}")
    public List<Product> getAlerts(@PathVariable Long userId) {
        return productService.getAlerts(userId);
    }
}
