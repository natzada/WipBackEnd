package com.rummikub.demo.controllers;

import com.rummikub.demo.dto.JwtResponse;
import com.rummikub.demo.dto.LoginRequest;
import com.rummikub.demo.entities.User;
import com.rummikub.demo.services.AuthService;
import com.rummikub.demo.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/send-verification")
    public ResponseEntity<?> sendVerificationCode(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            System.out.println("📧 Recebida requisição para: " + email);
            
            if (email == null || email.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email é obrigatório");
            }
            
            authService.startVerification(email);
            return ResponseEntity.ok("Código de verificação enviado para " + email);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String code = request.get("code");
            
            boolean isValid = verificationService.verifyCode(email, code);
            
            if (isValid) {
                return ResponseEntity.ok("Código válido");
            } else {
                return ResponseEntity.badRequest().body("Código inválido ou expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na verificação");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("🔐 Tentativa de login: " + loginRequest.getEmail());
            
            User user = authService.authenticate(loginRequest);
            
            if (user != null) {
                String mockToken = "mock-jwt-token-" + user.getId();
                
                JwtResponse response = new JwtResponse(
                    mockToken,
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCompanyName(),
                    user.getCreationDate(),
                    user.getProfilePicturePath(),
                    user.getPreferences()
                );
                
                System.out.println("✅ Login bem-sucedido - User ID: " + user.getId());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Erro no login: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro no login: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ AuthController está funcionando!");
    }
}