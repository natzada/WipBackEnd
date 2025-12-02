// src/main/java/com/rummikub/demo/controllers/SupplierController.java
package com.rummikub.demo.controllers;

import com.rummikub.demo.entities.Supplier;
import com.rummikub.demo.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://localhost:5173")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // POST criar fornecedor
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        try {
            System.out.println("🏢 Recebendo cadastro de fornecedor: " + supplier.getCompanyName());
            System.out.println("📧 Email: " + supplier.getContactEmail());
            System.out.println("👤 User ID: " + supplier.getUserId());
            
            Supplier savedSupplier = supplierService.createSupplier(supplier);
            System.out.println("✅ Fornecedor criado com ID: " + savedSupplier.getId());
            return ResponseEntity.ok(savedSupplier);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao criar fornecedor: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // GET fornecedores por usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Supplier>> getSuppliersByUser(@PathVariable Long userId) {
        try {
            System.out.println("👤 Buscando fornecedores para usuário: " + userId);
            List<Supplier> suppliers = supplierService.getSuppliersByUserId(userId);
            System.out.println("✅ Encontrados " + suppliers.size() + " fornecedores para usuário " + userId);
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar fornecedores: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET todos os fornecedores
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        try {
            System.out.println("📦 Buscando todos os fornecedores");
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            System.out.println("✅ Encontrados " + suppliers.size() + " fornecedores");
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar fornecedores: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET fornecedor por ID - CORRIGIDO
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        try {
            System.out.println("🔍 Buscando fornecedor ID: " + id);
            Optional<Supplier> supplier = supplierService.getSupplierById(id);
            
            if (supplier.isPresent()) {
                System.out.println("✅ Fornecedor encontrado: " + supplier.get().getCompanyName());
                return ResponseEntity.ok(supplier.get());
            } else {
                System.out.println("❌ Fornecedor não encontrado para ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar fornecedor: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}