package com.nhnacademy.resident.service.household_movement_address;

import com.nhnacademy.resident.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.RegisterHouseholdMovementAddressDto;
import com.nhnacademy.resident.domain.household_movement_address.UpdateHouseholdMovementAddressDto;

public interface HouseholdMovementAddressService {
    HouseholdMovementAddressDto addHouseholdMovementAddress(Long serialNumber, RegisterHouseholdMovementAddressDto dto);

    HouseholdMovementAddressDto update(Long householdSerialNumber, String reportDate, UpdateHouseholdMovementAddressDto dto);

    void delete(Long householdSerialNumber, String reportDate);
}