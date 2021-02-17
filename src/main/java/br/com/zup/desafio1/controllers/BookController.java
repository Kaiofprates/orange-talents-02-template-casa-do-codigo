package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.BookRequest;
import br.com.zup.desafio1.controllers.form.response.BookFindAllRespose;
import br.com.zup.desafio1.controllers.form.response.BookResponse;
import br.com.zup.desafio1.models.Book;
import br.com.zup.desafio1.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> newBook(@RequestBody @Valid BookRequest request) {
        Book book = request.toModel(manager);
        if (book != null) {
            manager.persist(book);
            return ResponseEntity.ok(new BookResponse(book).Response());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Book book = manager.find(Book.class, id);
        if (book != null) {
            return ResponseEntity.ok(new BookResponse(book).Response());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Query query = manager.createQuery("SELECT id, title FROM Book");
        List<BookFindAllRespose> response = query.getResultList();
        return ResponseEntity.ok(response);
    }


}
