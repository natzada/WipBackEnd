// src/main/java/com/rummikub/demo/repositories/SupplierRepository.java
package com.rummikub.demo.repositories;

import com.rummikub.demo.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByUserId(Long userId);
}