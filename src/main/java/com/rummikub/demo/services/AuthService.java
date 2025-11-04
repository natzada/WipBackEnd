package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
        System.out.println("🔐 AuthService - Tentando autenticar: " + loginRequest.getEmail());
        
        // Busca usuário pelo EMAIL com Optional
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> {
                    System.out.println("❌ Usuário não encontrado: " + loginRequest.getEmail());
                    return new RuntimeException("Usuário não encontrado");
                });
        
        System.out.println("✅ Usuário encontrado no banco - ID: " + user.getId());
        System.out.println("🔑 Senha do banco: " + user.getPassword());
        System.out.println("🔑 Senha recebida: " + loginRequest.getPassword());

        // VERIFICAÇÃO DE SENHA (texto puro por enquanto)
        if (user.getPassword().equals(loginRequest.getPassword())) {
            System.out.println("✅ Senha válida - Login bem-sucedido");
            return user;
        } else {
            System.out.println("❌ Senha inválida");
            throw new RuntimeException("Senha incorreta");
        }
    }

    public User register(SignUpRequest signUpRequest) {
        System.out.println("👤 AuthService - Registrando: " + signUpRequest.getEmail());
        
        // 1. Verificar se email já existe
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            System.out.println("❌ Email já cadastrado: " + signUpRequest.getEmail());
            throw new RuntimeException("Email já cadastrado");
        }

        // 2. Verificar código de verificação
        if (!verificationService.verifyCode(signUpRequest.getEmail(), signUpRequest.getVerificationCode())) {
            System.out.println("❌ Código de verificação inválido");
            throw new RuntimeException("Código de verificação inválido ou expirado");
        }

        // 3. Criar usuário
        User newUser = new User();
        newUser.setName(signUpRequest.getName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(signUpRequest.getPassword()); // Senha em texto por enquanto
        newUser.setCompanyName(signUpRequest.getCompanyName());
        newUser.setCreationDate(LocalDateTime.now());
        newUser.setPreferences("{}");
        newUser.setProfilePicturePath("");

        User savedUser = userRepository.save(newUser);
        
        System.out.println("✅ Usuário registrado com sucesso - ID: " + savedUser.getId());
        
        // 4. Remover código usado
        verificationService.removeCode(signUpRequest.getEmail());
        
        return savedUser;
    }
    
    public void startVerification(String email) {
        System.out.println("📧 AuthService - Iniciando verificação para: " + email);
        
        // Verificar se email já existe
        if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("❌ Email já cadastrado: " + email);
            throw new RuntimeException("Email já cadastrado");
        }
        verificationService.sendVerificationCode(email);
    }
}