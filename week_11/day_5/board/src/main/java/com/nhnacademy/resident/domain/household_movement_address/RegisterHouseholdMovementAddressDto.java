package com.nhnacademy.resident.domain.household_movement_address;

import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RegisterHouseholdMovementAddressDto {
    LocalDate houseMovementReportDate;
    String householdMovementAddress;
    HouseholdMovementAddress.LastAddressYn lastAddressYn;
}
