package br.com.zup.desafio1.controllers.form.request;

import br.com.zup.desafio1.models.Category;
import br.com.zup.desafio1.validate.UniqueValues.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank(message = "The name field cannot be empty")
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

     @Deprecated
     public CategoryRequest(){
     }

    public CategoryRequest(@NotBlank(message = "The name field cannot") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toModel() {
         Category category = new Category(this.name);
         return category;
    }
}
