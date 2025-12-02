package com.rummikub.demo.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Long id; // ← ADICIONAR ID
    private String email;
    private String companyName;
    private String creationDate;

    // Construtor completo
    public JwtResponse(String token, String username, Long id, String email, String companyName, String creationDate) {
        this.token = token;
        this.username = username;
        this.id = id;
        this.email = email;
        this.companyName = companyName;
        this.creationDate = creationDate;
    }

    // Getters e Setters para todos os campos
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public Long getId() { return id; } // ← GETTER DO ID
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
}