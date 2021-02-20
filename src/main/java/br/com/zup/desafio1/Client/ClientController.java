package br.com.zup.desafio1.Client;

import br.com.zup.desafio1.Client.form.ClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewClient(@RequestBody @Valid ClientRequest request){
        Client client = request.toModel(manager);
        if(client != null){
            manager.persist(client);
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.badRequest().build();
    }

}
