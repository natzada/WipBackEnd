// src/main/java/com/rummikub/demo/services/OrderServiceImpl.java
package com.rummikub.demo.services;

import com.rummikub.demo.entities.Order;
import com.rummikub.demo.entities.Product;
import com.rummikub.demo.entities.Supplier;
import com.rummikub.demo.entities.User;
import com.rummikub.demo.repositories.OrderRepository;
import com.rummikub.demo.repositories.ProductRepository;
import com.rummikub.demo.repositories.SupplierRepository;
import com.rummikub.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService; // Seu EmailService existente

    @Override
    public List<Order> getAllOrders() {
        System.out.println("📦 Buscando todos os pedidos no banco");
        List<Order> orders = orderRepository.findAll();
        System.out.println("✅ Encontrados " + orders.size() + " pedidos");
        return orders;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        System.out.println("👤 Buscando pedidos para usuário ID: " + userId);
        List<Order> userOrders = orderRepository.findByUserId(userId);
        System.out.println("✅ Encontrados " + userOrders.size() + " pedidos para usuário " + userId);
        return userOrders;
    }

    @Override
    public Order createOrder(Order order) {
        System.out.println("🆕 Criando novo pedido");
        
        // Validações básicas
        if (order.getProductId() == null) {
            throw new RuntimeException("ID do produto é obrigatório");
        }
        if (order.getSupplierId() == null) {
            throw new RuntimeException("ID do fornecedor é obrigatório");
        }
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }
        
        // Garantir que status não seja null
        if (order.getStatus() == null) {
            order.setStatus("PENDING");
        }
        
        Order savedOrder = orderRepository.save(order);
        System.out.println("✅ Pedido criado com ID: " + savedOrder.getId());

        // ENVIAR EMAIL PARA O FORNECEDOR
        try {
            sendOrderEmailToSupplier(savedOrder);
        } catch (Exception e) {
            System.err.println("⚠️ Erro ao enviar email, mas pedido foi criado: " + e.getMessage());
            // Não lançar exceção para não impedir a criação do pedido
        }
        
        return savedOrder;
    }

    private void sendOrderEmailToSupplier(Order order) {
        try {
            // Buscar informações completas
            Optional<Product> productOpt = productRepository.findById(order.getProductId());
            Optional<Supplier> supplierOpt = supplierRepository.findById(order.getSupplierId());
            Optional<User> userOpt = userRepository.findById(order.getUserId());

            if (productOpt.isPresent() && supplierOpt.isPresent() && userOpt.isPresent()) {
                Product product = productOpt.get();
                Supplier supplier = supplierOpt.get();
                User user = userOpt.get();

                // Usar seu método existente do EmailService
                emailService.sendOrderToSupplier(
                    supplier.getContactEmail(), 
                    supplier.getCompanyName(), 
                    order
                );
                
                System.out.println("📧 Email de pedido enviado para: " + supplier.getContactEmail());
                
            } else {
                System.err.println("❌ Dados incompletos para enviar email");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao enviar email para fornecedor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        System.out.println("🔍 Buscando pedido por ID: " + id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            System.out.println("✅ Pedido encontrado: ID " + order.get().getId());
        } else {
            System.out.println("❌ Pedido não encontrado para ID: " + id);
        }
        return order;
    }
}