package com.rummikub.demo.services;

import com.rummikub.demo.dto.UpdateProfileRequest;
import com.rummikub.demo.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getUser(Long id);
    User updateUser(User user); 
    User updateProfile(Long id, UpdateProfileRequest userDetails);; 
    User updateProfilePicture(Long userId, MultipartFile file);
    byte[] getProfilePicture(String fileName);
}