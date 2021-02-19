package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.PaymentRequest;
import br.com.zup.desafio1.models.Payment;
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
@RequestMapping("/payment")
public class PaymentController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createNewPayment(@RequestBody @Valid  PaymentRequest request){
        Payment payment = request.toModel(manager);
        if(payment != null){
            manager.persist(payment);
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.badRequest().build();
    }

}
