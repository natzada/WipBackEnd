// src/main/java/com/rummikub/demo/services/SupplierServiceImpl.java
package com.rummikub.demo.services;

import com.rummikub.demo.entities.Supplier;
import com.rummikub.demo.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        System.out.println("📦 Buscando todos os fornecedores no banco");
        List<Supplier> suppliers = supplierRepository.findAll();
        System.out.println("✅ Encontrados " + suppliers.size() + " fornecedores");
        return suppliers;
    }

    @Override
    public List<Supplier> getSuppliersByUserId(Long userId) {
        System.out.println("👤 Buscando fornecedores para usuário ID: " + userId);
        List<Supplier> userSuppliers = supplierRepository.findByUserId(userId);
        System.out.println("✅ Encontrados " + userSuppliers.size() + " fornecedores para usuário " + userId);
        return userSuppliers;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        System.out.println("🆕 Criando novo fornecedor: " + supplier.getCompanyName());
        
        // Validações básicas
        if (supplier.getCompanyName() == null || supplier.getCompanyName().trim().isEmpty()) {
            throw new RuntimeException("Nome da empresa é obrigatório");
        }
        if (supplier.getContactEmail() == null || supplier.getContactEmail().trim().isEmpty()) {
            throw new RuntimeException("Email de contato é obrigatório");
        }
        
        Supplier savedSupplier = supplierRepository.save(supplier);
        System.out.println("✅ Fornecedor criado com ID: " + savedSupplier.getId());
        return savedSupplier;
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        System.out.println("🔍 Buscando fornecedor por ID: " + id);
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            System.out.println("✅ Fornecedor encontrado: " + supplier.get().getCompanyName());
        } else {
            System.out.println("❌ Fornecedor não encontrado para ID: " + id);
        }
        return supplier;
    }
}