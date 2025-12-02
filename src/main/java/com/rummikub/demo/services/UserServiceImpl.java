// src/main/java/com/rummikub/demo/services/UserServiceImpl.java
package com.rummikub.demo.services;

import com.rummikub.demo.entities.User;
import com.rummikub.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        System.out.println("🔍 Buscando usuário por ID: " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        System.out.println("✅ Usuário encontrado: " + user.getName());
        return user;
    }

    @Override
    public User updateUser(User user) {
        System.out.println("✏️ Atualizando usuário ID: " + user.getId());
        
        // Verificar se o usuário existe
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("Usuário não encontrado com ID: " + user.getId());
        }

        // Validações básicas
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new RuntimeException("Nome é obrigatório");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email é obrigatório");
        }

        // Verificar se email já existe (excluindo o próprio usuário)
        User existingUserWithEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserWithEmail != null && !existingUserWithEmail.getId().equals(user.getId())) {
            throw new RuntimeException("Email já está em uso por outro usuário");
        }

        User updatedUser = userRepository.save(user);
        System.out.println("✅ Usuário atualizado: " + updatedUser.getName());
        return updatedUser;
    }
}