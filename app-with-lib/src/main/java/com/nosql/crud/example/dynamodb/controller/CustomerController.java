package com.nosql.crud.example.dynamodb.controller;

import com.nosql.crud.example.dynamodb.dtos.CustomerDTO;
import com.nosql.crud.example.dynamodb.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CustomerDTO customerDTO) {
        service.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> findById(@RequestParam UUID idPerson, @RequestParam UUID idAccount) {
        return service.findById(idPerson, idAccount)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
