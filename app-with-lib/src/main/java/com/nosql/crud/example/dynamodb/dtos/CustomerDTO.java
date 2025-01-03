package com.nosql.crud.example.dynamodb.dtos;

import com.nosql.crud.example.dynamodb.model.CustomerModel;
import jakarta.validation.constraints.NotNull;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.UUID;

import static com.nosql.crud.example.dynamodb.constants.CommonsConstants.EIGHTEEN_YEARS_OLD;

public class CustomerDTO {

    @NotNull(message = "idPerson is null")
    private UUID idPerson;

    @NotNull(message = "idAccount is null")
    private UUID idAccount;

    @NotNull(message = "birthdate is null")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    private Long expirationAt;

    public CustomerDTO() {
    }

    public CustomerDTO(UUID idPerson, UUID idAccount, LocalDate birthdate, Long expirationAt) {
        this.idPerson = idPerson;
        this.idAccount = idAccount;
        this.birthdate = birthdate;
        this.expirationAt = expirationAt;
    }

    public UUID getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(UUID idPerson) {
        this.idPerson = idPerson;
    }

    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
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

    @Override
    public String toString() {
        return "Customer{" +
                "idPerson=" + idPerson +
                ", idAccount=" + idAccount +
                ", birthdate=" + birthdate +
                ", expirationAt=" + expirationAt +
                '}';
    }

    public void calculateExpirationAt() {
        LocalDate eighteenthBirthday = birthdate.plusYears(EIGHTEEN_YEARS_OLD);
        LocalDateTime eighteenthBirthdayDateTime = eighteenthBirthday.toLocalDateTime(LocalTime.MIDNIGHT);
        this.expirationAt = eighteenthBirthdayDateTime.toDateTime().getMillis();
    }

    public CustomerModel toModel() {
        return new CustomerModel(idPerson, idAccount, birthdate, expirationAt);
    }
}
