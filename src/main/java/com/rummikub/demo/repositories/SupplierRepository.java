package com.rummikub.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rummikub.demo.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	
}
