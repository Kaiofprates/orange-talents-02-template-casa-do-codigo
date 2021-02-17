package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.CategoryRequest;
import br.com.zup.desafio1.models.Category;
import br.com.zup.desafio1.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicateNameValidator implements Validator {
    @Autowired
    private CategoryRepository repository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        CategoryRequest request = (CategoryRequest) target;
        Optional<Category> category =  repository.findByName(request.getName());
        if(category.isPresent()){
            errors.rejectValue("name", null, "The informed name already exists " + request.getName());
        }
    }


}
