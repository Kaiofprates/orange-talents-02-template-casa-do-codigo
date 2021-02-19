package br.com.zup.desafio1.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payment", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email", "document"})})

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Column(unique = true)
    private String document;
    @NotBlank
    private String address;
    @NotNull
    private Long number;
    @NotBlank
    private String complement;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private String state;
    @NotBlank
    private String phone;
    @NotBlank
    private String cep;

    @Deprecated
    public Payment() {
    }

    public Payment(@Email String email, @NotBlank String name, @NotBlank String surname, @NotBlank String document, @NotBlank String address, @NotNull Long number, @NotBlank String complement, @NotBlank String city, @NotBlank String country, String state, @NotBlank String phone, @NotBlank String cep) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
    }
}
