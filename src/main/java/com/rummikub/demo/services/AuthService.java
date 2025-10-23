package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rummikub.demo.dto.LoginRequest;
import com.rummikub.demo.dto.SignUpRequest;
import com.rummikub.demo.entities.User;
import com.rummikub.demo.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationService verificationService;

    public User authenticate(LoginRequest loginRequest) {
        // Busca usuário pelo EMAIL (já que não temos username)
        User user = userRepository.findByEmail(loginRequest.getEmail());
        
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return user; // Login bem-sucedido
        }
        return null; // Credenciais inválidas
    }

    public User register(SignUpRequest signUpRequest) {
        // 1. Verificar se email já existe
        if (userRepository.findByEmail(signUpRequest.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado");
        }

        // 2. Verificar código de verificação
        if (!verificationService.verifyCode(signUpRequest.getEmail(), signUpRequest.getVerificationCode())) {
            throw new RuntimeException("Código de verificação inválido ou expirado");
        }

        // 3. Criar usuário
        User newUser = new User();
        newUser.setName(signUpRequest.getName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(signUpRequest.getPassword());
        newUser.setPreferences("{}");

        User savedUser = userRepository.save(newUser);
        
        // 4. Remover código usado
        verificationService.removeCode(signUpRequest.getEmail());
        
        return savedUser;
    }
    
    public void startVerification(String email) {
        // Verificar se email já existe
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("Email já cadastrado");
        }
        verificationService.sendVerificationCode(email);
    }
}