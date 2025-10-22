package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationService {

    @Autowired
    private EmailService emailService;

    // Armazena códigos temporários: email -> {código, timestamp}
    private Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    private static class VerificationCode {
        String code;
        long createdAt;
        
        VerificationCode(String code) {
            this.code = code;
            this.createdAt = System.currentTimeMillis();
        }
        
        boolean isExpired() {
            // 10 minutos de expiração
            return System.currentTimeMillis() - createdAt > 10 * 60 * 1000;
        }
    }

    // Gera código de 6 dígitos
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // Envia código por email
    public void sendVerificationCode(String email) {
        String verificationCode = generateVerificationCode();
        
        // Salva no mapa
        verificationCodes.put(email, new VerificationCode(verificationCode));
        
        // Envia email
        emailService.sendVerificationCode(email, verificationCode);
        
        System.out.println("📧 Código enviado para " + email + ": " + verificationCode);
    }

    // Verifica se o código está correto
    public boolean verifyCode(String email, String code) {
        VerificationCode stored = verificationCodes.get(email);
        
        if (stored == null) {
            return false; // Código não existe
        }
        
        if (stored.isExpired()) {
            verificationCodes.remove(email); // Remove expirado
            return false;
        }
        
        return stored.code.equals(code);
    }

    // Remove código após uso
    public void removeCode(String email) {
        verificationCodes.remove(email);
    }

    // Limpa códigos expirados a cada minuto
    @Scheduled(fixedRate = 60000) // 1 minuto
    public void cleanupExpiredCodes() {
        long now = System.currentTimeMillis();
        verificationCodes.entrySet().removeIf(entry -> 
            entry.getValue().isExpired()
        );
    }
}