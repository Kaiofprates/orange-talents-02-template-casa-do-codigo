package br.com.zup.desafio1.Book.form;

import br.com.zup.desafio1.Author.Author;
import br.com.zup.desafio1.Book.Book;
import br.com.zup.desafio1.Category.Category;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class BookResponse {

    private String title;
    private String sumary;
    private BigDecimal price;
    private Long pages;
    private String isbn;
    private String publication;
    private Author author;
    private Category category;

    public BookResponse(Book book) {
        this.title = book.getTitle();
        this.sumary = book.getSumary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publication = book.getPublication().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.author = book.getAuthor();
        this.category = book.getCategory();

    }

    public BookResponse Response() {
        return this;
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

    public String getPublication() {
        return publication;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }
}
