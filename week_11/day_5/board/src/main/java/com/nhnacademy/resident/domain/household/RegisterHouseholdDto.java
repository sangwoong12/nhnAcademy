package com.nhnacademy.resident.domain.household;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RegisterHouseholdDto {
    @NotNull
    private Long householdSerialNumber;
    @NotNull
    private Long householdResidentSerialNumber;
    @NotNull
    private LocalDate householdCompositionDate;
    @NotNull
    private String householdCompositionReasonCode;
    @NotNull
    private String currentHouseMovementAddress;
}
