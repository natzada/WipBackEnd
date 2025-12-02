// src/main/java/com/rummikub/demo/controllers/UserController.java
package com.rummikub.demo.controllers;

import com.rummikub.demo.entities.User;
import com.rummikub.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        try {
            System.out.println("🔍 Buscando usuário ID: " + id);
            User user = userService.getUser(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar usuário: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            System.out.println("✏️ Atualizando usuário ID: " + id);
            System.out.println("📊 Dados recebidos: " + user);
            
            // Garantir que o ID do path seja usado
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            
            System.out.println("✅ Usuário atualizado: " + updatedUser.getName());
            return ResponseEntity.ok(updatedUser);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar usuário: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint específico para atualizar perfil
    @PutMapping("/{id}/profile")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody Map<String, String> profileData) {
        try {
            System.out.println("👤 Atualizando perfil do usuário ID: " + id);
            System.out.println("📝 Dados do perfil: " + profileData);
            
            String name = profileData.get("name");
            String email = profileData.get("email");
            String companyName = profileData.get("companyName");
            String preferences = profileData.get("preferences");
            
            // Buscar usuário existente
            User existingUser = userService.getUser(id);
            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Atualizar apenas os campos permitidos
            if (name != null) existingUser.setName(name);
            if (email != null) existingUser.setEmail(email);
            if (companyName != null) existingUser.setCompanyName(companyName);
            if (preferences != null) existingUser.setPreferences(preferences);
            
            User updatedUser = userService.updateUser(existingUser);
            
            System.out.println("✅ Perfil atualizado com sucesso");
            return ResponseEntity.ok(updatedUser);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar perfil: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint para upload de foto de perfil
    @PutMapping("/{id}/profile-picture")
    public ResponseEntity<User> uploadProfilePicture(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("🖼️ Recebendo upload de foto para usuário ID: " + id);
            System.out.println("📁 Arquivo: " + file.getOriginalFilename());
            System.out.println("📏 Tamanho: " + file.getSize() + " bytes");
            
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Validar tipo de arquivo
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Validar tamanho do arquivo (máx 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Buscar usuário
            User existingUser = userService.getUser(id);
            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Aqui você pode:
            // 1. Salvar o arquivo no sistema de arquivos
            // 2. Salvar o arquivo no banco de dados
            // 3. Fazer upload para um serviço de cloud (AWS S3, etc.)
            
            // Por enquanto, vamos apenas simular o salvamento
            String fileName = "profile_" + id + "_" + System.currentTimeMillis() + 
                             getFileExtension(file.getOriginalFilename());
            
            // Simular salvamento - em produção, implemente isso
            System.out.println("💾 Simulando salvamento do arquivo: " + fileName);
            
            // Atualizar o caminho da foto no usuário
            existingUser.setProfilePicturePath(fileName);
            User updatedUser = userService.updateUser(existingUser);
            
            System.out.println("✅ Foto de perfil atualizada: " + fileName);
            return ResponseEntity.ok(updatedUser);
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao fazer upload da foto: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Método auxiliar para obter extensão do arquivo
    private String getFileExtension(String fileName) {
        if (fileName == null) return "";
        int lastDot = fileName.lastIndexOf(".");
        return lastDot > 0 ? fileName.substring(lastDot) : "";
    }

    // Endpoint para servir foto de perfil
    @GetMapping("/profile-picture/{filename}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String filename) {
        try {
            System.out.println("🖼️ Solicitando foto: " + filename);
            
            // Em produção, busque o arquivo do sistema de arquivos ou storage
            // Por enquanto, retornar uma imagem padrão ou 404
            
            // Simular que a foto não existe
            return ResponseEntity.notFound().build();
            
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar foto: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}