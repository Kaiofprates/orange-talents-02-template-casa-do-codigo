package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.BookRequest;
import br.com.zup.desafio1.models.Author;
import br.com.zup.desafio1.models.Book;
import br.com.zup.desafio1.models.Category;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public  String newBook(@RequestBody @Valid BookRequest request){
        Author author = manager.find(Author.class,request.getAuthorId());
        Category category = manager.find(Category.class, request.getCategoryId());

        if(author != null && category != null){
            Book book = request.toModel(author,category);
            manager.persist(book);
            return book.toString();
        }
        return "Autor ou categoria não encontradas";


    }


}