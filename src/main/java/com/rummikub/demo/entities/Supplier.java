package com.rummikub.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;
    private String email; // NOVO CAMPO: email específico para pedidos
    private String apiUrl;

    // Mantenha os getters e setters existentes
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public String getApiUrl() {
        return apiUrl;
    }
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    
    // NOVO: Getter para email com fallback
    public String getEmail() {
        if (this.email != null && !this.email.trim().isEmpty()) {
            return this.email;
        }
        // Fallback: tenta extrair email do contactInfo
        return extractEmailFromContactInfo();
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    private String extractEmailFromContactInfo() {
        if (contactInfo == null) return null;
        
        // Lógica simples para extrair email
        String[] parts = contactInfo.split("\\s+");
        for (String part : parts) {
            if (part.contains("@") && part.contains(".")) {
                return part.trim();
            }
        }
        return null;
    }
}