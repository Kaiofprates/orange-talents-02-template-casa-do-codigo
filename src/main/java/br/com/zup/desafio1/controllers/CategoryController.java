package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.CategoryRequest;
import br.com.zup.desafio1.controllers.form.response.CategoryResponse;
import br.com.zup.desafio1.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> newCategory(@RequestBody @Valid CategoryRequest form) {
        Category category = form.toModel();
        em.persist(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoryResponse(category).Response());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategorie(@PathVariable Long id){
        Category category = em.find(Category.class,id);
        if(category != null ){
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CategoryRequest form){
        Category category = em.find(Category.class,id);
        if(category != null){
            category.setName(form.getName());
            em.persist(category);
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){
        Category category = em.find(Category.class,id);
        if(category != null){
            em.remove(category);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
