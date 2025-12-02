// src/main/java/com/rummikub/demo/entities/Order.java
package com.rummikub.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_id", nullable = false)
    private Long productId;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;
    
    @Column(name = "status")
    private String status = "PENDING";
    
    // Construtores
    public Order() {}
    
    public Order(Long productId, Integer quantity, Long supplierId, Long userId, LocalDate orderDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.userId = userId;
        this.orderDate = orderDate;
    }
    
    @PrePersist
    protected void onCreate() {
        if (orderDate == null) {
            orderDate = LocalDate.now();
        }
        if (status == null) {
            status = "PENDING";
        }
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}