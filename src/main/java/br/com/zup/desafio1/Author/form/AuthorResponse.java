package br.com.zup.desafio1.Author.form;

import br.com.zup.desafio1.Author.Author;

import java.time.LocalDateTime;

public class AuthorResponse {
    private Long id;
    private String name;
    private String email;
    private String description;
    private LocalDateTime register;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.description = author.getDescription();
        this.register = author.getRegister();
    }

    public AuthorResponse Respose() {
        return this;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getRegister() {
        return register;
    }

}
