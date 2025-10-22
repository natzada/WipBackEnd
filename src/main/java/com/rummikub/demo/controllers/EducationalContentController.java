package com.rummikub.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rummikub.demo.entities.EducationalContent;
import com.rummikub.demo.services.EducationalContentService;

@RestController
@RequestMapping("/api/contents")
public class EducationalContentController {

    @Autowired
    private EducationalContentService contentService;

    // retornA umA listA dE todoS oS conteúdoS educacionaiS
    public List<EducationalContent> getAllContents() {
        // chamA o métodO getAllContents do serviçO parA obteR a listA
        return contentService.getAllContents();
    }
}

// EducationalContent: 
// É umA classE q guardA dadoS dE conteúdoS educacionaiS (tipO aulaS, vídeoS, etc.), coM coisaS comO: títulO e descriçãO.

// FunçãO do códigO: 
// CriA uM endpoinT /apI/contents q, quandO chamadO via GET, devolvE umA listA dE todoS oS conteúdoS educacionaiS eM JSON.