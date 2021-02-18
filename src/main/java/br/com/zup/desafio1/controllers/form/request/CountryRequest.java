package br.com.zup.desafio1.controllers.form.request;

import br.com.zup.desafio1.models.Country;
import br.com.zup.desafio1.validate.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CountryRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldName = "name")
    private String name;

    public CountryRequest(String name) {
        this.name = name;
    }

    @Deprecated
    public CountryRequest(){
    }

    public String getName() {
        return name;
    }

    public Country toModel() {
        Country country = new Country(this.name);
        return country;
    }
}
