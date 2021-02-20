package br.com.zup.desafio1.Country;

import br.com.zup.desafio1.Country.form.CountryFindAllRespose;
import br.com.zup.desafio1.Country.form.CountryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createCountry(@RequestBody @Valid CountryRequest request) {
        Country country = request.toModel();
        if (country != null) {
            manager.persist(country);
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.badRequest().build();
    }

    /*
     Criei esse endpoint tendo como pressuposto que para evetuar a compra
     o front end precisa de uma lista de paises e seus respectivos estados
     para o formulário de pagamento
     */
    @GetMapping
    @Transactional
    public List<CountryFindAllRespose> findAll() {
        return manager.createQuery("select c from Country c", Country.class)
                .getResultStream()
                .map(CountryFindAllRespose::new)
                .collect(Collectors.toList());
    }


}
