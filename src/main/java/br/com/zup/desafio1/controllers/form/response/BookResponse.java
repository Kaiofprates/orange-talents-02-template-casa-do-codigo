package br.com.zup.desafio1.controllers.form.response;

import br.com.zup.desafio1.models.Author;
import br.com.zup.desafio1.models.Book;
import br.com.zup.desafio1.models.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookResponse {
    private Long id;
    private String title;
    private String sumary;
    private BigDecimal price;
    private Long pages;
    private String isbn;
    private LocalDate publication;
    private Category category;
    private Author author;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.sumary = book.getSumary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publication = book.getPublication();
        this.category = book.getCategory();
        this.author = book.getAuthor();
    }

    public BookResponse Response(){ return  this;}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSumary() {
        return sumary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
