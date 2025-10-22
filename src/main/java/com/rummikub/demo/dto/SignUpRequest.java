package com.rummikub.demo.dto;

public class SignUpRequest {
    private String name;     // Mudou de username para name
    private String email;
    private String password;
    private String verificationCode;

    public SignUpRequest() {}
    
    public SignUpRequest(String name, String email, String password, String verificationCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
    }

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }
}