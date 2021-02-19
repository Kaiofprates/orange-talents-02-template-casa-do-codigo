package br.com.zup.desafio1.controllers.form.response;

import br.com.zup.desafio1.models.Country;

public class CountryFindAllRespose {
    private Country country;

    @Deprecated
    public CountryFindAllRespose() {
    }

    public CountryFindAllRespose(Country response) {
        this.country = response;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
