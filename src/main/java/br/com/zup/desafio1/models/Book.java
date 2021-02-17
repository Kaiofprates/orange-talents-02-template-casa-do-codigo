package br.com.zup.desafio1.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String title;
    private String sumary;
    private BigDecimal price;
    private Long pages;
    private String isbn;
    private LocalDate publication;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private  Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private  Author author;

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sumary='" + sumary + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", isbn='" + isbn + '\'' +
                ", publication=" + publication +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}
