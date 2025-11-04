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

    @Autowired(required = false)
    private JavaMailSender mailSender;

    // Método existente para código de verificação
    public void sendVerificationCode(String toEmail, String verificationCode) {
        boolean emailEnviado = false;
        
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

    // NOVO MÉTODO: Enviar email para fornecedor com pedido
    public void sendOrderToSupplier(String toEmail, String supplierName, Order order) {
        if (mailSender == null) {
            System.out.println("📧 [SIMULAÇÃO] Email para fornecedor: " + toEmail);
            System.out.println("📦 Pedido #" + order.getId() + " para " + supplierName);
            return;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(toEmail);
            helper.setSubject("Novo Pedido Recebido - #" + order.getId());
            helper.setText(buildOrderEmailContent(supplierName, order), true);
            
            mailSender.send(message);
            System.out.println("✅ Email de pedido enviado para fornecedor: " + toEmail);
            
        } catch (MessagingException e) {
            System.err.println("❌ Falha ao enviar email para fornecedor: " + e.getMessage());
        }
    }

    // Método para construir o conteúdo HTML do email
    private String buildOrderEmailContent(String supplierName, Order order) {
        return "<!DOCTYPE html>" +
                "<html lang='pt-BR'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <style>" +
                "        body { font-family: Arial, sans-serif; color: #333; }" +
                "        .header { background-color: #2E86AB; color: white; padding: 20px; text-align: center; }" +
                "        .content { padding: 20px; }" +
                "        .order-info { background-color: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px; }" +
                "        .product-table { width: 100%; border-collapse: collapse; margin: 20px 0; }" +
                "        .product-table th { background-color: #2E86AB; color: white; padding: 10px; text-align: left; }" +
                "        .product-table td { padding: 10px; border-bottom: 1px solid #ddd; }" +
                "        .total { text-align: right; font-size: 18px; font-weight: bold; margin-top: 20px; }" +
                "        .footer { margin-top: 30px; padding: 20px; background-color: #f1f1f1; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class='header'>" +
                "        <h1>Novo Pedido Recebido</h1>" +
                "    </div>" +
                "    <div class='content'>" +
                "        <p>Prezado <strong>" + supplierName + "</strong>,</p>" +
                "        <p>Você recebeu um novo pedido através do nosso sistema.</p>" +
                "        " +
                "        <div class='order-info'>" +
                "            <h3>Informações do Pedido</h3>" +
                "            <p><strong>Número do Pedido:</strong> #" + order.getId() + "</p>" +
                "            <p><strong>Data do Pedido:</strong> " + order.getOrderDate() + "</p>" +
                "            <p><strong>Cliente:</strong> " + (order.getUser() != null ? order.getUser().getName() : "N/A") + "</p>" +
                "        </div>" +
                "        " +
                "        <h3>Detalhes do Produto</h3>" +
                "        <table class='product-table'>" +
                "            <thead>" +
                "                <tr>" +
                "                    <th>Produto</th>" +
                "                    <th>Quantidade</th>" +
                "                </tr>" +
                "            </thead>" +
                "            <tbody>" +
                "                <tr>" +
                "                    <td>" + (order.getProduct() != null ? order.getProduct().getName() : "Produto") + "</td>" +
                "                    <td>" + order.getQuantity() + " unidades</td>" +
                "                </tr>" +
                "            </tbody>" +
                "        </table>" +
                "        " +
                "        <div class='footer'>" +
                "            <p>Atenciosamente,<br><strong>Equipe Work In Progress</strong></p>" +
                "            <p><em>Este é um email automático, por favor não responda.</em></p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}	