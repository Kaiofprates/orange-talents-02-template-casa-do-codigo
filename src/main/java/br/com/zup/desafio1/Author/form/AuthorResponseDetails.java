package br.com.zup.desafio1.Author.form;

/*
 * Classe criada para auxiliar no retorno do endpoint de detalhes do livro
 */


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
