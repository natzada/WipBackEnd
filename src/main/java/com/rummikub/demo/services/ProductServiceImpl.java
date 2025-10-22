package com.rummikub.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rummikub.demo.entities.Product;
import com.rummikub.demo.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // PegA todoS oS produtoS dE uM usuáriO específicO
    // FiltrA a listA do bancO p retornaR sÓ oS produtoS do userId
    // ... mostrA o "carrinhO" oU estoquE d uM usuáriO 🛒
    @Override
    public List<Product> getProductsByUser(Long userId) {
        return productRepository.findAll().stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .toList(); // FiltrA produtoS pelO ID do usuáriO
    }

    // PegA produtoS d uM usuáriO q venceM noS próximoS 7 diaS
    // UsA a data atuaL + 7 diaS prA buscaR no bancO
    // ...É comO uM alertA dE "corrE, teU produtO tA quaE vencendO!" 🔔
    @Override
    public List<Product> getAlerts(Long userId) {
        return null;
      //  return productRepository.findProductsNearExpiration(userId, LocalDate.now().plusDays(7));
    }
}