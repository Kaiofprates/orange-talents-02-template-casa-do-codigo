package br.com.zup.desafio1.controllers.form.request;

import br.com.zup.desafio1.handler.exceptions.StateDuplicateException;
import br.com.zup.desafio1.models.Country;
import br.com.zup.desafio1.models.Payment;
import br.com.zup.desafio1.validate.IdExists.ExistId;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PaymentRequest {

    //@UniqueValue(domainClass = Payment.class, fieldName = "email")
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @CPF
    //@UniqueValue(domainClass = Payment.class, fieldName = "document")
    private String document;
    @NotBlank
    private String address;
    @NotNull
    private Long number;
    @NotBlank
    private String complement;
    @NotBlank
    private String city;
    @NotBlank
    @ExistId(domainClass = Country.class, fieldName = "name")
    private String country;

    private String state;
    @NotBlank
    private String phone;
    @NotBlank
    private String cep;

    @Deprecated
    public PaymentRequest() {
    }

    public Payment toModel(EntityManager manager) {
        Payment payment = new Payment("email.com.br", "32212011");
        if (this.state != null) {
            Query query = manager.createQuery("select s from State s where s.countryName = :country and s.name =:name");
            query.setParameter("country", this.country);
            query.setParameter("name", this.state);
            List<?> list = query.getResultList();
            if (list.isEmpty()) {
                throw new StateDuplicateException("The informed state does not exist in that country");
            }
        }
        return payment;
    }

    public PaymentRequest(@Email String email, @NotBlank String name, @NotBlank String surname, @NotBlank @CPF String document, @NotBlank String address, @NotNull Long number, @NotBlank String complement, @NotBlank String city, @NotBlank String country, String state, @NotBlank String phone, @NotBlank String cep) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.state = state;
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

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getCep() {
        return cep;
    }
}
