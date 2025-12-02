package com.rummikub.demo.dto;

public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String verificationCode;
    private String companyName; // ← VERIFICAR SE EXISTE ESTE CAMPO
    
    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
    
    public String getCompanyName() { return companyName; } // ← GETTER
    public void setCompanyName(String companyName) { this.companyName = companyName; } // ← SETTER
}