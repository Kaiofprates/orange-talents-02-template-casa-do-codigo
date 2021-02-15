package br.com.zup.desafio1.controllers.form;

import br.com.zup.desafio1.models.Author;

public class AuthorForm {
    private String name;
    private String email;
    private String description;

    public AuthorForm(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
    public Author convert(){
        Author author = new Author(this.name,this.email,this.description);
        return author;
    }
}
