package com.rummikub.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rummikub.demo.entities.Order;
import com.rummikub.demo.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // RespondE a POST /apI/orders, criandO uM novO pedidO
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order); // ChamA o serviçO prA criaR o pedidO
    }
}

//CriA uM endpoinT /apI/orders quE recebE uM POST coM dadoS dE uM pedidO (eM JSON),
// usA o serviçO prA salvaR o pedidO e devolvE o pedidO criadO.
// Order é umA classE quE representA uM pedidO (tipO comprA), coM dadoS comO ID, clientE, itenS, etc.

// EX: É comO "caixA" dE umA lojA online, registrA o pedidO e confirmA pro clientE 