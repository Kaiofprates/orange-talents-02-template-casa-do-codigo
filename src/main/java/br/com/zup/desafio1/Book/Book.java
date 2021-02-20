package br.com.zup.desafio1.Book;

import br.com.zup.desafio1.Author.Author;
import br.com.zup.desafio1.Category.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book", uniqueConstraints = {@UniqueConstraint(columnNames = {"title","id","isbn"})})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private String sumary;
    private BigDecimal price;
    private Long pages;
    @Column(unique = true)
    private String isbn;
    private LocalDate publication;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(String title, String sumary, BigDecimal price, Long pages, String isbn, LocalDate publication, Category category, Author author) {
        this.title = title;
        this.sumary = sumary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publication = publication;
        this.category = category;
        this.author = author;
    }

    public Long getId() { return id; }

    public String getTitle() { return title;}

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
