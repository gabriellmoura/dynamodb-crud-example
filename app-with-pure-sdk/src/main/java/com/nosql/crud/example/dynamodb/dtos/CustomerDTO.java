package com.nosql.crud.example.dynamodb.dtos;

import com.nosql.crud.example.dynamodb.model.CustomerModel;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static com.nosql.crud.example.dynamodb.constants.CommonsConstants.EIGHTEEN_YEARS_OLD;

public record CustomerDTO(    @NotNull(message = "idPerson is null") UUID idPerson,
                              @NotNull(message = "idAccount is null") UUID idAccount,
                              @NotNull(message = "birthdate is null") LocalDate birthdate
) {

    public long calculateExpirationAt() {
        LocalDate eighteenthBirthday = birthdate.plusYears(EIGHTEEN_YEARS_OLD);
        LocalDateTime eighteenthBirthdayDateTime = eighteenthBirthday.toLocalDateTime(LocalTime.MIDNIGHT);
        return eighteenthBirthdayDateTime.toDateTime().getMillis();
    }

    public CustomerModel toModel() {
        return new CustomerModel(idPerson, idAccount, birthdate, calculateExpirationAt());
    }
}
