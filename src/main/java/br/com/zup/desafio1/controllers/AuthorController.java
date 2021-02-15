package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.AuthorForm;
import br.com.zup.desafio1.models.Author;
import br.com.zup.desafio1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @PostMapping
    public ResponseEntity<?> newAuthor(@RequestBody @Valid AuthorForm form){
     Author author =  repository.save(form.convert());
     return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }


}
