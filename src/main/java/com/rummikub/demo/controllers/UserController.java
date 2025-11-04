
package com.rummikub.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rummikub.demo.dto.UpdateProfileRequest;
import com.rummikub.demo.entities.User;
import com.rummikub.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Mantém o original para compatibilidade
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    // Novo endpoint para atualização completa do perfil
    @PutMapping("/{id}/profile")
    public User updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest userDetails) {
        return userService.updateProfile(id, userDetails);
    }

    // Endpoint para upload de foto de perfil
    @PutMapping(value = "/{id}/profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updateProfilePicture(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        
        if (file.isEmpty()) {
            throw new RuntimeException("Please select a file to upload");
        }
        
        return userService.updateProfilePicture(id, file);
    }

    // Endpoint para servir a imagem
    @GetMapping("/profile-picture/{fileName:.+}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String fileName) {
        byte[] imageData = userService.getProfilePicture(fileName);
        
        // Detecta o tipo de imagem automaticamente
        MediaType mediaType = MediaType.IMAGE_JPEG;
        if (fileName.toLowerCase().endsWith(".png")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            mediaType = MediaType.IMAGE_GIF;
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentLength(imageData.length);
        
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
