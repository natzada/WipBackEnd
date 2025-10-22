package com.rummikub.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rummikub.demo.entities.EducationalContent;

public interface EducationalContentRepository extends JpaRepository<EducationalContent, Long> {
}