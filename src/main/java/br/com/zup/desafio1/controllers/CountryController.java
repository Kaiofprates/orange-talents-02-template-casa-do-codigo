package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.CountryRequest;
import br.com.zup.desafio1.models.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/country")
public class CountryController {

    @PersistenceContext
    private EntityManager  manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createCountry(@RequestBody @Valid CountryRequest request){
        Country country = request.toModel();
        if(country != null){
            manager.persist(country);
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.badRequest().build();
    }


}
