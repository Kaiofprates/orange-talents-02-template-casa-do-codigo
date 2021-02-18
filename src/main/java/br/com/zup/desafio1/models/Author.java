package br.com.zup.desafio1.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(columnNames = {"email","id"})})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(max = 400)
    private String description;
    @DateTimeFormat
    private LocalDateTime register = LocalDateTime.now();

    @Deprecated
    public Author() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Author(@NotBlank String name, @NotBlank @Email String email, @NotBlank @Size(max = 400) String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
