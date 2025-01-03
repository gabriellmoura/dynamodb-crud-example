package com.nosql.crud.example.dynamodb.repository;

import com.nosql.crud.example.dynamodb.model.CustomerModel;
import com.nosql.crud.example.dynamodb.model.pk.CustomerPK;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface CustomerRepository extends CrudRepository<CustomerModel, CustomerPK> {

    Optional<CustomerModel> findById(CustomerPK customerPK);
}
