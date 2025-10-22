package com.rummikub.demo.services;

import com.rummikub.demo.entities.Order;
import com.rummikub.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        return orderRepository.save(order);
    }
}

// ImplementA o métodO prA criaR uM pedidO
// Order é umA classE coM dadoS tipO ID, clieNte, itEns, etc.
// Define a data do pedidO comO agorA e salvA no bancO

// EX: É comO o "caixA" q registrA a comprA coM a data atuaL e guardA no sistemA