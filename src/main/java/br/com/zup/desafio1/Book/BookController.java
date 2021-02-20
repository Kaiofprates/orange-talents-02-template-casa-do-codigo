package br.com.zup.desafio1.Book;

import br.com.zup.desafio1.Book.form.BookDetailsResponse;
import br.com.zup.desafio1.Book.form.BookFindAllRespose;
import br.com.zup.desafio1.Book.form.BookRequest;
import br.com.zup.desafio1.Book.form.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Book book = manager.find(Book.class, id);
        if (book != null) {
            return ResponseEntity.ok(new BookDetailsResponse(book).response());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Transactional
    public List<BookFindAllRespose> findAll() {

        return manager.createQuery("select b from Book b", Book.class)
                .getResultStream()
                .map(BookFindAllRespose::new)
                .collect(Collectors.toList());
    }


}
