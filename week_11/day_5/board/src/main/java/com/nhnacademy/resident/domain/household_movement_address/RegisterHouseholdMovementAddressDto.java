package com.nhnacademy.resident.domain.household_movement_address;

import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RegisterHouseholdMovementAddressDto {
    @NotNull
    LocalDate houseMovementReportDate;
    @NotNull
    String householdMovementAddress;
    @NotNull
    HouseholdMovementAddress.LastAddressYn lastAddressYn;
}
