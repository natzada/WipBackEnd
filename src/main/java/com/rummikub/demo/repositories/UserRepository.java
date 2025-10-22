package com.rummikub.demo.repositories;

import com.rummikub.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);
}