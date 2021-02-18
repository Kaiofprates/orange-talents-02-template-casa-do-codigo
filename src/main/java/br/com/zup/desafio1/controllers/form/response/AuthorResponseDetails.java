package br.com.zup.desafio1.controllers.form.response;

public class AuthorResponseDetails {
    private String name;
    private String description;

    public AuthorResponseDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Deprecated
    public AuthorResponseDetails() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
