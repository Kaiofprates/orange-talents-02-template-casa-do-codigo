package br.com.zup.desafio1.models;

import javax.persistence.*;

@Entity
@Table(name = "state", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class State {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "country_id")
    private Long country;

    private String countryName;

    @Deprecated
    public State(){
    }

    public State(String name, Long country, String countryName) {
        this.name = name;
        this.country = country;
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryName() {
        return countryName;
    }
}
