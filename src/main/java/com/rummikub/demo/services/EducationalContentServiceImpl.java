package com.rummikub.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rummikub.demo.entities.EducationalContent;
import com.rummikub.demo.repositories.EducationalContentRepository;

@Service
public class EducationalContentServiceImpl implements EducationalContentService {

    @Autowired
    private EducationalContentRepository contentRepository;

    @Override
    public List<EducationalContent> getAllContents() {
        return contentRepository.findAll();
    }
}

// ImplementA o métodO dA interface prA pegaR todoS oS conteúdoS
// EducationalContent é umA classE coM dadoS tipO títuLo, descriÇão, etc.

// EX: É comO o caraA quE vaI no bancO dE dadosS e pegA o catáLogo dE conteúdoS prA entregaR