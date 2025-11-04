package com.rummikub.demo.services;

import com.rummikub.demo.entities.Order;
import com.rummikub.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        // Define a data do pedido
        order.setOrderDate(LocalDateTime.now());
        
        // Salva o pedido no banco
        Order savedOrder = orderRepository.save(order);
        
        // ENVIA EMAIL PARA O FORNECEDOR
        if (order.getSupplier() != null && order.getSupplier().getContactInfo() != null) {
            try {
                String supplierEmail = extractEmailFromContactInfo(order.getSupplier().getContactInfo());
                String supplierName = order.getSupplier().getName();
                
                if (supplierEmail != null) {
                    emailService.sendOrderToSupplier(supplierEmail, supplierName, savedOrder);
                } else {
                    System.out.println("⚠️ Email do fornecedor não encontrado no contactInfo: " + order.getSupplier().getContactInfo());
                }
            } catch (Exception e) {
                System.err.println("❌ Erro ao enviar email para fornecedor: " + e.getMessage());
                // Não lança exceção para não quebrar o fluxo do pedido
            }
        } else {
            System.out.println("⚠️ Fornecedor ou informações de contato não disponíveis para o pedido");
        }
        
        return savedOrder;
    }

    // Método auxiliar para extrair email do contactInfo
    private String extractEmailFromContactInfo(String contactInfo) {
        if (contactInfo == null) return null;
        
        // Simples validação de email - você pode melhorar isso
        if (contactInfo.contains("@")) {
            // Procura por um email no texto
            String[] parts = contactInfo.split("\\s+");
            for (String part : parts) {
                if (part.contains("@") && part.contains(".")) {
                    return part.trim();
                }
            }
        }
        return null;
    }
}