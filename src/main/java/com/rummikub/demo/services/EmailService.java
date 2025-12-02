// src/main/java/com/rummikub/demo/services/EmailService.java
package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rummikub.demo.entities.Order;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

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
        
        // SEMPRE mostra no console (fallback)
        System.out.println("\n" + "🎯".repeat(25));
        System.out.println("📧 VERIFICAÇÃO DE EMAIL - WORK IN PROGRESS");
        System.out.println("📍 Para: " + toEmail);
        System.out.println("🔐 Código de Verificação: " + verificationCode);
        if (!emailEnviado) {
            System.out.println("💡 EMAIL NÃO CONFIGURADO - Use o código acima");
        }
        System.out.println("🎯".repeat(25) + "\n");
    }
    
 // Atualize o método sendOrderToSupplier no seu EmailService existente
    public void sendOrderToSupplier(String toEmail, String supplierName, Order order) {
        if (mailSender == null) {
            System.out.println("\n" + "📧".repeat(30));
            System.out.println("📦 NOVO PEDIDO PARA FORNECEDOR");
            System.out.println("📍 Para: " + toEmail);
            System.out.println("🏢 Fornecedor: " + supplierName);
            System.out.println("🆔 Pedido ID: #" + order.getId());
            System.out.println("📅 Data: " + order.getOrderDate());
            System.out.println("📊 Status: " + order.getStatus());
            System.out.println("📧".repeat(30) + "\n");
            return;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(toEmail);
            helper.setSubject("📦 Novo Pedido Recebido - #" + order.getId());
            
            String emailContent = String.format("""
                <html>
                <body>
                    <h2>Novo Pedido Recebido!</h2>
                    <p>Prezado(a) <strong>%s</strong>,</p>
                    
                    <p>Você recebeu um novo pedido através do nosso sistema.</p>
                    
                    <h3>📋 Detalhes do Pedido:</h3>
                    <ul>
                        <li><strong>Número do Pedido:</strong> #%d</li>
                        <li><strong>Data:</strong> %s</li>
                        <li><strong>Status:</strong> %s</li>
                    </ul>
                    
                    <p>Por favor, acesse o sistema para ver os detalhes completos do pedido.</p>
                    
                    <p>Atenciosamente,<br>
                    <strong>Sistema de Pedidos</strong></p>
                </body>
                </html>
                """, supplierName, order.getId(), order.getOrderDate(), order.getStatus());
            
            helper.setText(emailContent, true);
            mailSender.send(message);
            System.out.println("✅ Email de pedido enviado para fornecedor: " + toEmail);
            
        } catch (MessagingException e) {
            System.err.println("❌ Falha ao enviar email para fornecedor: " + e.getMessage());
        }
    }
}