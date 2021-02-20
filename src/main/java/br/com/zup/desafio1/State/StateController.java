package br.com.zup.desafio1.State;

import br.com.zup.desafio1.State.form.StateRequest;
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
@RequestMapping("/state")
public class StateController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createState(@RequestBody @Valid StateRequest request){
        State state = request.toModel(manager);
        if(state != null){
            manager.persist(state);
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.badRequest().build();
    }
}
