// src/main/java/com/rummikub/demo/services/OrderService.java
package com.rummikub.demo.services;

import com.rummikub.demo.entities.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
}