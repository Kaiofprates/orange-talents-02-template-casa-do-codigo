package br.com.zup.desafio1.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "country", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank
    private String name;

    @OneToMany
    @JoinColumn(name = "country_id")
    private List<State> states;

    @Deprecated
    public Country() {
    }

    public Country(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<State> getEstates() {
        return states;
    }

    public void setEstates(List<State> states) {
        this.states = states;
    }

    public Long getId() {
        return id;
    }
}

