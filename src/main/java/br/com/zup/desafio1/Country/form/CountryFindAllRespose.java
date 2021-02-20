package br.com.zup.desafio1.Country.form;

import br.com.zup.desafio1.Country.Country;

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
