package com.nhnacademy.resident.domain.household;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RegisterHouseholdDto {
    private Long householdSerialNumber;
    private Long householdResidentSerialNumber;
    private LocalDate householdCompositionDate;
    private String householdCompositionReasonCode;
    private String currentHouseMovementAddress;
}
