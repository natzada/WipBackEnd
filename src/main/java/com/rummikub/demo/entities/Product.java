// src/main/java/com/rummikub/demo/entities/Product.java
package com.rummikub.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    
    @Column(name = "user_id")
    private Long userId;
    
    // Construtores
    public Product() {}
    
    public Product(String name, Integer quantity, LocalDate expirationDate, Long userId) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", userId=" + userId +
                '}';
    }
}