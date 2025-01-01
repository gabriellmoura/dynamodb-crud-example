package com.nosql.crud.example.dynamodb.service;

import com.nosql.crud.example.dynamodb.dtos.CustomerDTO;
import com.nosql.crud.example.dynamodb.model.pk.CustomerPK;
import com.nosql.crud.example.dynamodb.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void save(CustomerDTO customerDTO) {
        customerDTO.calculateExpirationAt();
        final var customerModel = repository.save(customerDTO.toModel());
        log.info("Customer {} saved successfully", customerModel);
    }

    public Optional<CustomerDTO> findById(UUID idPerson, UUID idAccount) {
        final var customerModelOptional = repository.findById(new CustomerPK(idPerson, idAccount));
        if (customerModelOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(customerModelOptional.get().toDTO());
    }
}
