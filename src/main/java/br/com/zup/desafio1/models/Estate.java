package br.com.zup.desafio1.models;

import br.com.zup.desafio1.validate.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estate", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Estate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "country_id")
    private Long country;

    private String countryName;

    @Deprecated
    public Estate(){
    }

    public Estate(String name, Long country,String countryName) {
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
