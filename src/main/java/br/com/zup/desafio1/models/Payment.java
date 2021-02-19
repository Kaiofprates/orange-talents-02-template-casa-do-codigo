package br.com.zup.desafio1.models;

import javax.persistence.*;

@Entity
@Table(name = "payment", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email", "document"})})

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;
    @Deprecated
    public Payment() {
    }

    public Payment(String email, String document) {
        this.email = email;
        this.document = document;
    }
}
