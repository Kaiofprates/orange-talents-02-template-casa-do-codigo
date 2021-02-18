package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.CountryRequest;
import br.com.zup.desafio1.controllers.form.response.BookDetailsResponse;
import br.com.zup.desafio1.controllers.form.response.BookFindAllRespose;
import br.com.zup.desafio1.models.Book;
import br.com.zup.desafio1.models.Category;
import br.com.zup.desafio1.models.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Long id){
        Country country = manager.find(Country.class,id);
        if(country != null ){
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.notFound().build();
    }


}
