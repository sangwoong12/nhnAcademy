package com.nhnacademy.resident.domain.household_movement_address;

import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UpdateHouseholdMovementAddressDto {
    @NotNull
    String householdMovementAddress;
    @NotNull
    HouseholdMovementAddress.LastAddressYn lastAddressYn;
}
