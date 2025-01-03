package com.nosql.crud.example.dynamodb.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nosql.crud.example.dynamodb.dtos.CustomerDTO;
import com.nosql.crud.example.dynamodb.model.CustomerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {


    private final DynamoDBMapper repository;


    public CustomerService(DynamoDBMapper repository) {
        this.repository = repository;
    }

    public void save(CustomerDTO customerDTO) {
        customerDTO.calculateExpirationAt();
        repository.save(customerDTO.toModel());
        log.info("Customer {} saved successfully", customerDTO);
    }

    public Optional<CustomerDTO> findById(UUID idPerson, UUID idAccount) {
        final var customerModel = repository.load(CustomerModel.class, idPerson, idAccount);
        if (customerModel == null) {
            return Optional.empty();
        }
        return Optional.of(customerModel.toDTO());
    }
}
