package br.com.zup.desafio1.Country.form;

import br.com.zup.desafio1.Country.Country;
import br.com.zup.desafio1.validate.UniqueValues.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CountryRequest {

    @NotBlank(message = "this field cannot be empty")
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
