package br.com.zup.desafio1.Author;

import br.com.zup.desafio1.Author.form.AuthorRequest;
import br.com.zup.desafio1.Author.form.AuthorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> newAuthor(@RequestBody @Valid AuthorRequest form) {
        Author author = form.toModel();
        em.persist(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthorResponse(author).Respose());
    }


}
