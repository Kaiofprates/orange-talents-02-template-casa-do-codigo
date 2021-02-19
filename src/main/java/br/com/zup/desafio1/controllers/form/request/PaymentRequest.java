package br.com.zup.desafio1.controllers.form.request;

import antlr.PythonCharFormatter;
import br.com.zup.desafio1.handler.exceptions.CustonMessageException;
import br.com.zup.desafio1.models.Country;
import br.com.zup.desafio1.models.Payment;
import br.com.zup.desafio1.validate.CnpjORCpf.CnpjOrCpf;
import br.com.zup.desafio1.validate.CpfOrCnpjValidator.CPFOuCNPJ;
import br.com.zup.desafio1.validate.IdExists.ExistId;
import br.com.zup.desafio1.validate.UniqueValues.UniqueValue;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
//@CnpjOrCpf(domainClass = PaymentRequest.class,fieldName = {"document"})
public class PaymentRequest {

    @UniqueValue(domainClass = Payment.class, fieldName = "email")
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @UniqueValue(domainClass = Payment.class, fieldName = "document")
    @CPFOuCNPJ
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
    // A regra de negócio vai exigir uma rota de consulta que retorne todos os estados de um país.
    private String state;
    @NotBlank
    private String phone;
    @NotBlank
    private String cep;

    @Deprecated
    public PaymentRequest() {
    }

    public Payment toModel(EntityManager manager) {
        Payment payment = new Payment(
                this.email,
                this.name,
                this.surname,
                this.document,
                this.address,
                this.number,
                this.complement,
                this.city,
                this.country,
                this.state,
                this.phone,
                this.cep
        );
        /*
        *  Criei essa lógica para ( caso um nome de estado seja passado )
        *  validar no banco de dados a sua relação com o pais informado
        */

        if (this.state != null) {
            Query query = manager.createQuery("select s from State s where s.countryName = :country and s.name =:name");
            query.setParameter("country", this.country);
            query.setParameter("name", this.state);
            List<?> list = query.getResultList();
            if (list.isEmpty()) {
                throw new CustonMessageException("The informed state does not exist in that country");
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
