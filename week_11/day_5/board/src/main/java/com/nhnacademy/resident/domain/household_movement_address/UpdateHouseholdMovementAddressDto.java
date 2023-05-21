package com.nhnacademy.resident.domain.household_movement_address;

import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateHouseholdMovementAddressDto {
    String householdMovementAddress;
    HouseholdMovementAddress.LastAddressYn lastAddressYn;
}
