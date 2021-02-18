package br.com.zup.desafio1.controllers;

import br.com.zup.desafio1.controllers.form.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    public ResponseEntity<?> createNewPayment(@RequestBody PaymentRequest request){
        return ResponseEntity.ok(request);
    }

}
