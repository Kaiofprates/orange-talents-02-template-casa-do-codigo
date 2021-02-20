package br.com.zup.desafio1.Category;

import br.com.zup.desafio1.Category.form.CategoryRequest;
import br.com.zup.desafio1.Category.form.CategoryResponse;
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


}
