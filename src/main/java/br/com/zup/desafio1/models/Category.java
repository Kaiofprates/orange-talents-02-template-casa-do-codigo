package br.com.zup.desafio1.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    @Deprecated
    public Category() {
    }

    public String getName() {
        return name;
    }

    public String setName(@NotBlank String name){
        return this.name = name;
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
