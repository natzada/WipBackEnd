package com.rummikub.demo.services;

import com.rummikub.demo.entities.Order;

public interface OrderService {
    Order createOrder(Order order);
}

// MétodO quE criA uM novO pedidO e o retornA
// Order é umA classE quE representA uM pediDo, coM dadoS tipO ID, clieNte, itEns, etc.

// EX: É coM o "gerentE" q recebE uM pedidO e registrA elE no sistemA