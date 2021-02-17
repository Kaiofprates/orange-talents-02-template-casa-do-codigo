package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.BookRequest;
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
          return request.toString();
    }


}
