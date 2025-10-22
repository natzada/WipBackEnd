package com.rummikub.demo.services;

import com.rummikub.demo.entities.User;

public interface UserService {
    User getUser(Long id);
    User updateUser(User user);
}
