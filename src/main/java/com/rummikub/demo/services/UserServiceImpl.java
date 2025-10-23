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

    @Override
    public User updateProfile(Long id, UpdateProfileRequest userDetails) {
        User user = getUser(id);
        
        if (userDetails.getName() != null) {
            user.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getCompanyName() != null) {
            user.setCompanyName(userDetails.getCompanyName());
        }
        if (userDetails.getPreferences() != null) {
            user.setPreferences(userDetails.getPreferences());
        }
        
        return userRepository.save(user);
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