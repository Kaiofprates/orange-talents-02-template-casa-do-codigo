package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.AuthorRequest;
import br.com.zup.desafio1.controllers.repository.AuthorRepository;
import br.com.zup.desafio1.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicateEmailValidator implements Validator {

    @Autowired
    private AuthorRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return AuthorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        AuthorRequest request = (AuthorRequest) target;
        Optional<Author> author =  repository.findByEmail(request.getEmail());
        if(author.isPresent()){
            errors.rejectValue("email", null, "The informed email already exists " + request.getEmail());
        }
    }
}
