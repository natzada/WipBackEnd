package com.rummikub.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rummikub.demo.entities.Supplier;
import com.rummikub.demo.services.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // CrA uM endpoinT /apI/suppliers quE, coM GET, devolvE umA listA dE fornecedoreS eM JSON.
    // Supplier é umA classE quE representA uM FornecedoR, coM dadoS tipO ID, nOme, contAto, etc.
    // EX: É comO pegaR o catÁlogo dE fornecedoreS do sistemA e mostraR pro cliEnte 
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }
}