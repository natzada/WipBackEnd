// src/main/java/com/rummikub/demo/entities/Supplier.java
package com.rummikub.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "company_name", nullable = false)
    private String companyName;
    
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "products")
    private String products;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Construtores
    public Supplier() {}
    
    public Supplier(String companyName, String contactEmail, String phone, String address, String products, Long userId) {
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.address = address;
        this.products = products;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getProducts() { return products; }
    public void setProducts(String products) { this.products = products; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}