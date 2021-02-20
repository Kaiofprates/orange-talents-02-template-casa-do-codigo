package br.com.zup.desafio1.Client;

import javax.persistence.*;

@Entity
@Table(name = "payment", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "email", "document"})})

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    @Column(unique = true)
    private String document;
    private String address;
    private Long number;
    private String complement;
    private String city;
    private String country;
    private String state;
    private String phone;
    private String cep;

    @Deprecated
    public Client() {
    }

    public Client(String email, String name, String surname,
                  String document, String address, Long number,
                  String complement, String city, String country,
                  String state, String phone, String cep) {
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
