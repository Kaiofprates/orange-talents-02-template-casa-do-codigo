package br.com.zup.desafio1.controllers.form.request;

import br.com.zup.desafio1.models.Country;
import br.com.zup.desafio1.models.Estate;
import br.com.zup.desafio1.validate.UniqueValue;
import com.sun.source.tree.CompilationUnitTree;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class EstateRequest {
    @NotBlank
    @UniqueValue(domainClass = Estate.class, fieldName = "name")
    private String name;
    @NotNull
    private Long countryId;

    public EstateRequest(String name, Long coutryId) {
        this.name = name;
        this.countryId = coutryId;
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }

    @Transactional
    public Estate toModel(EntityManager manager) {
      @NotNull Country country = manager.find(Country.class,this.countryId);
      Assert.state(country != null, "Country not found");
      Estate estate = new Estate(this.name,country.getId(),country.getName());
      return estate;
    }
}
