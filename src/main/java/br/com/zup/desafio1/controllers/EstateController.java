package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.EstateRequest;
import br.com.zup.desafio1.models.Estate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estate")
public class EstateController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createEstate(@RequestBody @Valid EstateRequest request){
        Estate estate = request.toModel(manager);
        if(estate != null){
            manager.persist(estate);
            return ResponseEntity.ok(estate);
        }
        return ResponseEntity.badRequest().build();
    }
}
