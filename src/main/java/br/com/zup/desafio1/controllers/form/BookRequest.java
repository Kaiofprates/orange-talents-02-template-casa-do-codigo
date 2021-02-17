package br.com.zup.desafio1.controllers.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookRequest {

    private String title;
    private String resume;
    private String sumary;
    private BigDecimal price;
    private Long pages;
    private String isbn;
    private LocalDate publication;
    private Long categoryId;
    private Long authorId;

    public BookRequest(String title,
                       String resume,
                       String sumary,
                       BigDecimal price,
                       Long pages,
                       String isbn,
                       LocalDate publication,
                       Long categoryId,
                       Long authorId) {
        this.title = title;
        this.resume = resume;
        this.sumary = sumary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publication = publication;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "title='" + title + '\'' +
                ", resume='" + resume + '\'' +
                ", sumary='" + sumary + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", isbn='" + isbn + '\'' +
                ", publication=" + publication +
                ", categoryId=" + categoryId +
                ", authoId=" + authorId +
                '}';
    }
}
