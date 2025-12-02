// src/main/java/com/rummikub/demo/services/SupplierService.java
package com.rummikub.demo.services;

import com.rummikub.demo.entities.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    List<Supplier> getSuppliersByUserId(Long userId);
    Supplier createSupplier(Supplier supplier);
    Optional<Supplier> getSupplierById(Long id);
}