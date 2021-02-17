package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.BookRequest;
import br.com.zup.desafio1.models.Author;
import br.com.zup.desafio1.models.Book;
import br.com.zup.desafio1.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> newBook(@RequestBody @Valid BookRequest request){
        Book book = request.toModel(manager);
        if(book != null){
            manager.persist(book);
            return ResponseEntity.ok(book.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


    }


}
