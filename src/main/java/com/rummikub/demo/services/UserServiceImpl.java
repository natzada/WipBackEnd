package com.rummikub.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rummikub.demo.dto.UpdateProfileRequest;
import com.rummikub.demo.entities.User;
import com.rummikub.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

 // UserServiceImpl.java - no método updateProfile
    @Override
    public User updateProfile(Long id, UpdateProfileRequest userDetails) {
        User user = getUser(id);
        
        System.out.println("🔄 Atualizando perfil do usuário ID: " + id);
        System.out.println("📥 Dados recebidos - Name: " + userDetails.getName());
        System.out.println("📥 Dados recebidos - Email: " + userDetails.getEmail());
        
        // ✅ CORREÇÃO: Só atualiza o nome se for fornecido e não vazio
        if (userDetails.getName() != null && !userDetails.getName().trim().isEmpty()) {
            user.setName(userDetails.getName());
            System.out.println("✅ Name atualizado para: " + userDetails.getName());
        } else {
            // ❌ NÃO FAZ NADA - mantém o nome original
            System.out.println("ℹ️ Name mantido como: " + user.getName());
        }
        
        // Email continua obrigatório
        if (userDetails.getEmail() != null && !userDetails.getEmail().trim().isEmpty()) {
            user.setEmail(userDetails.getEmail());
            System.out.println("✅ Email atualizado para: " + userDetails.getEmail());
        } else {
            throw new RuntimeException("Email é obrigatório");
        }
        
        // Campos opcionais
        if (userDetails.getCompanyName() != null) {
            user.setCompanyName(userDetails.getCompanyName());
            System.out.println("✅ CompanyName atualizado para: " + userDetails.getCompanyName());
        }
        
        if (userDetails.getPreferences() != null) {
            user.setPreferences(userDetails.getPreferences());
            System.out.println("✅ Preferences atualizadas");
        }
        
        User savedUser = userRepository.save(user);
        System.out.println("💾 Usuário salvo no banco - Name: " + savedUser.getName());
        
        return savedUser;
    }


    @Override
    public User updateProfilePicture(Long userId, MultipartFile file) {
        User user = getUser(userId);
        
        // Deleta a imagem anterior se existir
        if (user.getProfilePicturePath() != null) {
            fileStorageService.deleteFile(user.getProfilePicturePath());
        }
        
        // Armazena a nova imagem
        String fileName = fileStorageService.storeFile(file);
        user.setProfilePicturePath(fileName);
        
        return userRepository.save(user);
    }

    @Override
    public byte[] getProfilePicture(String fileName) {
        return fileStorageService.loadFile(fileName);
    }
}