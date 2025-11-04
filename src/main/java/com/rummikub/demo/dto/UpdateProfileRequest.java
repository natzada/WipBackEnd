// dto/UpdateProfileRequest.java
package com.rummikub.demo.dto;

public class UpdateProfileRequest {
    private String name;        // ← AGORA OPCIONAL
    private String email;       // ← OBRIGATÓRIO
    private String companyName; // ← OPCIONAL
    private String preferences; // ← OPCIONAL

    // Construtores
    public UpdateProfileRequest() {}

    public UpdateProfileRequest(String name, String email, String companyName, String preferences) {
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.preferences = preferences;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}