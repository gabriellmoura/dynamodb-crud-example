package com.nosql.crud.example.dynamodb.model.pk;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

import java.util.UUID;

@DynamoDBDocument
public class CustomerPK {

    private UUID idPerson;

    private UUID idAccount;

    public CustomerPK() {
    }

    public CustomerPK(UUID idPerson, UUID idAccount) {
        this.idPerson = idPerson;
        this.idAccount = idAccount;
    }

    @DynamoDBHashKey(attributeName = "id_pessoa")
    public UUID getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(UUID idPerson) {
        this.idPerson = idPerson;
    }

    @DynamoDBRangeKey(attributeName = "id_conta")
    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "CustomerPK{" +
                "idPerson=" + idPerson +
                ", idAccount=" + idAccount +
                '}';
    }
}
