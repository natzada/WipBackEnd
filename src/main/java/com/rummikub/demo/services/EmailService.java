// src/main/java/com/rummikub/demo/services/EmailService.java
package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired(required = false) // ← Torna opcional
    private JavaMailSender mailSender;

    public void sendVerificationCode(String toEmail, String verificationCode) {
        boolean emailEnviado = false;
        
        // Tenta enviar email real SE configurado
        if (mailSender != null) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toEmail);
                message.setSubject("Código de Verificação - Work In Progress");
                message.setText(
                    "Olá!\n\n" +
                    "Seu código de verificação é: " + verificationCode + "\n\n" +
                    "Use este código para completar seu cadastro.\n" +
                    "Este código expira em 10 minutos.\n\n" +
                    "Atenciosamente,\nEquipe Work In Progress"
                );
                
                mailSender.send(message);
                emailEnviado = true;
                System.out.println("✅ Email REAL enviado para: " + toEmail);
                
            } catch (Exception e) {
                System.err.println("❌ Falha no email real: " + e.getMessage());
            }
        }
        
    
    }
}