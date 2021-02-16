package br.com.zup.desafio1.controllers.form;

import br.com.zup.desafio1.models.Author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthorRequest {
    @NotBlank(message = "The name field cannot be empty")
    private String name;
    @NotBlank(message = "The email field cannot be empty")
    @Email(message = "Enter a valid email")
    private String email;
    @NotEmpty(message = "The description field cannot be empty")
    @Size(max = 400, message = "The maximum number of words is 400")
    private String description;

    public AuthorRequest(@NotBlank String name, @NotBlank @Email String email, @NotEmpty @Size(max = 400) String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public Author toModel(){
        Author author = new Author(this.name,this.email,this.description);
        return author;
    }
}
