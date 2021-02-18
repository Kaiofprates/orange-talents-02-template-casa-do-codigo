package br.com.zup.desafio1.controllers.form.request;

import br.com.zup.desafio1.models.Payment;
import br.com.zup.desafio1.validate.UniqueValue;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PaymentRequest {
    @Email
    @UniqueValue(domainClass = Payment.class, fieldName = "email")
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    @CPF
    @UniqueValue(domainClass = Payment.class, fieldName = "document")
    private String document;
    @NotEmpty
    private String address;
    @NotEmpty
    private Long number;
    @NotEmpty
    private String complement;
    @NotEmpty
    private String city;
    private Long countryId;
    private Long estateId;
    private String phone;
    private String cep;

    @Deprecated
    public PaymentRequest(){
    }

    public PaymentRequest(String email, String name, String surname,
                          String document, String address, Long number,
                          String complement, String city, Long countryId,
                          Long estateId, String phone, String cep) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.estateId = estateId;
        this.phone = phone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public Long getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getEstateId() {
        return estateId;
    }

    public String getPhone() {
        return phone;
    }

    public String getCep() {
        return cep;
    }
}
