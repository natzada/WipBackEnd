package com.rummikub.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rummikub.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
