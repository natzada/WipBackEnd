package com.rummikub.demo.controllers;

import com.rummikub.demo.entities.Product;
import com.rummikub.demo.services.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    // GET todos os produtos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            System.out.println("📦 Recebida requisição para buscar todos os produtos");
            List<Product> products = productServiceImpl.getAllProducts();
            System.out.println("✅ Retornando " + products.size() + " produtos");
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar produtos: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // GET produtos por usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUser(@PathVariable Long userId) {
        try {
            System.out.println("👤 Recebida requisição para produtos do usuário: " + userId);
            List<Product> products = productServiceImpl.getProductsByUserId(userId);
            System.out.println("✅ Retornando " + products.size() + " produtos para usuário " + userId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar produtos do usuário: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // POST criar produto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            System.out.println("🆕 Recebida requisição para criar produto");
            System.out.println("📊 Dados recebidos: " + product);
            
            Product savedProduct = productServiceImpl.createProduct(product);
            System.out.println("✅ Produto criado com sucesso: " + savedProduct);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            System.err.println("❌ Erro ao criar produto: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            System.out.println("✏️ Recebida requisição para atualizar produto ID: " + id);
            product.setId(id); // Garantir que o ID do path seja usado
            Product updatedProduct = productServiceImpl.updateProduct(product);
            System.out.println("✅ Produto atualizado com sucesso");
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            System.out.println("🗑️ Recebida requisição para deletar produto ID: " + id);
            productServiceImpl.deleteProduct(id);
            System.out.println("✅ Produto deletado com sucesso");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("❌ Erro ao deletar produto: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // GET produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            System.out.println("🔍 Recebida requisição para buscar produto ID: " + id);
            return productServiceImpl.getProductById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar produto: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint mock temporário para testes
    @GetMapping("/mock")
    public ResponseEntity<List<Product>> getMockProducts() {
        try {
            System.out.println("🎭 Retornando produtos mock para testes");
            
            // Criar produtos mock
            Product product1 = new Product();
            product1.setId(1L);
            product1.setName("Arroz");
            product1.setQuantity(10);
            product1.setUserId(1L);

            Product product2 = new Product();
            product2.setId(2L);
            product2.setName("Feijão");
            product2.setQuantity(5);
            product2.setUserId(1L);

            Product product3 = new Product();
            product3.setId(3L);
            product3.setName("Açúcar");
            product3.setQuantity(8);
            product3.setUserId(1L);

            List<Product> mockProducts = List.of(product1, product2, product3);
            
            System.out.println("✅ Retornando " + mockProducts.size() + " produtos mock");
            return ResponseEntity.ok(mockProducts);
        } catch (Exception e) {
            System.err.println("❌ Erro no endpoint mock: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}