package br.com.zup.desafio1.Book.form;

import br.com.zup.desafio1.Book.Book;

public class BookFindAllRespose {
    private String title;
    private Long id;

    public BookFindAllRespose(Book book) {
        this.title = book.getTitle();
        this.id = book.getId();
    }
    @Deprecated
    public BookFindAllRespose() {
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
