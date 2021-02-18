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
    private List<Estate> estates;

    @Deprecated
    public Country() {
    }

    public Country(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }

    public Long getId() {
        return id;
    }
}

