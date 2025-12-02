// src/main/java/com/rummikub/demo/repositories/OrderRepository.java
package com.rummikub.demo.repositories;

import com.rummikub.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}