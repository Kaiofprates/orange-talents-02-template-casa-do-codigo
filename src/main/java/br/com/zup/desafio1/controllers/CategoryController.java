package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.AuthorRequest;
import br.com.zup.desafio1.controllers.form.CategoryRequest;
import br.com.zup.desafio1.models.Author;
import br.com.zup.desafio1.models.Category;
import br.com.zup.desafio1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private DuplicateNameValidator duplicateNameValidator;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(duplicateNameValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> newCategory(@RequestBody @Valid CategoryRequest form) {
        Category category = form.toModel();
        em.persist(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category.toString());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCategorie(@PathVariable Long id){
        Optional<Category> category = repository.findById(id);
        if(category.isPresent()){
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
