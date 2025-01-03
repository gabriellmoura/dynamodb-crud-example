package com.nosql.crud.example.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.nosql.crud.example.dynamodb.converters.LocalDateToStringConverter;
import com.nosql.crud.example.dynamodb.dtos.CustomerDTO;
import com.nosql.crud.example.dynamodb.model.pk.CustomerPK;
import org.joda.time.LocalDate;

import java.util.UUID;

@DynamoDBTable(tableName = "customer")
public class CustomerModel {


//    @DynamoDBIgnore
    private CustomerPK customerPK;

    @DynamoDBAttribute(attributeName = "birthdate")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S)
    @DynamoDBTypeConverted(converter = LocalDateToStringConverter.class)
    private LocalDate birthdate;

    @DynamoDBAttribute(attributeName = "expiration_at")
    private Long expirationAt;

    public CustomerModel() {
    }

    public CustomerModel(CustomerPK customerPK, LocalDate birthdate, Long expirationAt) {
        this.customerPK = customerPK;
        this.birthdate = birthdate;
        this.expirationAt = expirationAt;
    }

    public CustomerModel(UUID idPerson, UUID idAccount, LocalDate birthdate, Long expirationAt) {
        this.customerPK = new CustomerPK(idPerson, idAccount);
        this.birthdate = birthdate;
        this.expirationAt = expirationAt;
    }

    public CustomerPK getCustomerPK() {
        return customerPK;
    }

    public void setCustomerPK(CustomerPK customerPK) {
        this.customerPK = customerPK;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Long getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(Long expirationAt) {
        this.expirationAt = expirationAt;
    }

    @DynamoDBHashKey(attributeName = "id_pessoa")
    public UUID getPartitionKey() {
        return customerPK != null ? customerPK.getIdPerson()
                : null;
    }

    public void setPartitionKey(final UUID idPerson) {
        if (customerPK == null) {
            customerPK = new CustomerPK();
        }
        customerPK.setIdPerson(idPerson);
    }

    @DynamoDBRangeKey(attributeName = "id_conta")
    public UUID getSortKey() {
        return customerPK != null ? customerPK.getIdAccount()
                : null;
    }

    public void setSortKey(final UUID idAccount) {
        if (customerPK == null) {
            customerPK = new CustomerPK();
        }
        customerPK.setIdAccount(idAccount);
    }

    public CustomerDTO toDTO() {
        return new CustomerDTO(customerPK.getIdPerson(), customerPK.getIdAccount(), birthdate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerPK=" + customerPK +
                ", birthdate=" + birthdate +
                ", expirationAt=" + expirationAt +
                '}';
    }
}
