// dto/JwtResponse.java
package com.rummikub.demo.dto;

import java.time.LocalDateTime;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String email;
    private String companyName;
    private LocalDateTime creationDate;
    private String profilePicturePath;
    private String preferences;

    // Construtor COMPLETO (novo)
    public JwtResponse(String token, Long id, String name, String email, 
                      String companyName, LocalDateTime creationDate, 
                      String profilePicturePath, String preferences) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.creationDate = creationDate;
        this.profilePicturePath = profilePicturePath;
        this.preferences = preferences;
    }

    // Construtor ANTIGO (mantenha para compatibilidade)
    public JwtResponse(String token, String name) {
        this.token = token;
        this.name = name;
        // Defina valores padrão para os outros campos
        this.id = 0L;
        this.email = "";
        this.companyName = "";
        this.creationDate = LocalDateTime.now();
        this.profilePicturePath = "";
        this.preferences = "";
    }

    // Getters e Setters para TODOS os campos
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    
    public String getProfilePicturePath() { return profilePicturePath; }
    public void setProfilePicturePath(String profilePicturePath) { this.profilePicturePath = profilePicturePath; }
    
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}