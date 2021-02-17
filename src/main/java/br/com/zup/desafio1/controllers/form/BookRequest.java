package br.com.zup.desafio1.controllers.form;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookRequest {

    @NotBlank private String title;
    @NotBlank  @Size(max = 500) private String resume;
    private String sumary;
    @NotNull @Min(20)
    private BigDecimal price;
    @NotNull @Min(100)
    private Long pages;
    @NotEmpty
    private String isbn;
    private LocalDate publication;
    @NotNull
    private Long categoryId;
    @NotNull
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
