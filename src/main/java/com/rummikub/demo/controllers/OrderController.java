// src/main/java/com/rummikub/demo/controllers/OrderController.java
package com.rummikub.demo.controllers;

import com.rummikub.demo.entities.Order;
import com.rummikub.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // POST criar pedido
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            System.out.println("📦 Recebendo novo pedido");
            System.out.println("📊 Dados: " + order);
            System.out.println("👤 User ID: " + order.getUserId());
            System.out.println("🆔 Product ID: " + order.getProductId());
            System.out.println("🏢 Supplier ID: " + order.getSupplierId());
            
            Order savedOrder = orderService.createOrder(order);
            System.out.println("✅ Pedido criado com ID: " + savedOrder.getId());
            return ResponseEntity.ok(savedOrder);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao criar pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // GET pedidos por usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        try {
            System.out.println("👤 Buscando pedidos para usuário: " + userId);
            List<Order> orders = orderService.getOrdersByUserId(userId);
            System.out.println("✅ Encontrados " + orders.size() + " pedidos para usuário " + userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar pedidos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET todos os pedidos
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            System.out.println("📦 Buscando todos os pedidos");
            List<Order> orders = orderService.getAllOrders();
            System.out.println("✅ Encontrados " + orders.size() + " pedidos");
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar pedidos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try {
            System.out.println("🔍 Buscando pedido ID: " + id);
            Optional<Order> order = orderService.getOrderById(id);
            
            if (order.isPresent()) {
                System.out.println("✅ Pedido encontrado: " + order.get().getId());
                return ResponseEntity.ok(order.get());
            } else {
                System.out.println("❌ Pedido não encontrado para ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}