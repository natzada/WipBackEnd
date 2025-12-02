package com.rummikub.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estoque")
@CrossOrigin(origins = "http://localhost:5173")
public class StockController {

    @GetMapping
    public ResponseEntity<?> getEstoque(@RequestHeader("Authorization") String authHeader) {
        try {
            // Validar token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Não autorizado");
            }
            
            String token = authHeader.substring(7);
            if (!token.startsWith("mock-jwt-token-")) {
                return ResponseEntity.status(401).body("Token inválido");
            }
            
            // Simular dados do estoque
            Map<String, Object> estoque = Map.of(
                "expressoes", List.of(
                    Map.of("id", 1, "nome", "Expressão 1", "quantidade", 10),
                    Map.of("id", 2, "nome", "Expressão 2", "quantidade", 5)
                ),
                "totalItens", 15
            );
            
            return ResponseEntity.ok(estoque);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao carregar estoque");
        }
    }
}