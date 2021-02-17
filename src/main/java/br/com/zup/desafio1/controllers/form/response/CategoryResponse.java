package br.com.zup.desafio1.controllers.form.response;


import br.com.zup.desafio1.models.Category;

public class CategoryResponse {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public CategoryResponse Response(){return this;}

}
