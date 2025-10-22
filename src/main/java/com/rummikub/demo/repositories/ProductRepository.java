package com.rummikub.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rummikub.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	    @Query("SELECT p FROM Product p WHERE p.user.id = :userId AND p.expirationDate <= :date")
	    List<Product> findProductsNearExpiration(Long userId, LocalDate date);
}

