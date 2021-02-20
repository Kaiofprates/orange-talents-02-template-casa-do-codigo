package br.com.zup.desafio1.Client.form;

import br.com.zup.desafio1.handler.exceptions.CustonMessageException;
import br.com.zup.desafio1.Country.Country;
import br.com.zup.desafio1.Client.Client;
import br.com.zup.desafio1.validate.CpfOrCnpjValidator.CPFOuCNPJ;
import br.com.zup.desafio1.validate.IdExists.ExistId;
import br.com.zup.desafio1.validate.UniqueValues.UniqueValue;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClientRequest {

    @UniqueValue(domainClass = Client.class, fieldName = "email")
    @Email
    private String email;
    @NotBlank(message = "this field cannot be empty")
    private String name;
    @NotBlank(message = "this field cannot be empty")
    private String surname;
    @NotBlank(message = "this field cannot be empty")
    @UniqueValue(domainClass = Client.class, fieldName = "document")
    @CPFOuCNPJ
    private String document;
    @NotBlank(message = "this field cannot be empty")
    private String address;
    @NotNull(message = "this field cannot be empty")
    private Long number;
    @NotBlank(message = "this field cannot be empty")
    private String complement;
    @NotBlank(message = "this field cannot be empty")
    private String city;
    @NotBlank(message = "this field cannot be empty")
    /*
    Faço a validação pelo nome do pais para facilitar ao front
     */
    @ExistId(domainClass = Country.class, fieldName = "name")
    private String country;
    // A regra de negócio vai exigir uma rota de consulta que retorne todos os estados de um país.
    private String state;
    @NotBlank(message = "this field cannot be empty")
    private String phone;
    @NotBlank(message = "this field cannot be empty")
    private String cep;

    @Deprecated
    public ClientRequest() {
    }

    public Client toModel(EntityManager manager) {
        Client client = new Client(
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
        return client;
    }

    public ClientRequest(@Email String email, @NotBlank String name, @NotBlank String surname, @NotBlank  String document, @NotBlank String address, @NotNull Long number, @NotBlank String complement, @NotBlank String city, @NotBlank String country, String state, @NotBlank String phone, @NotBlank String cep) {
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
