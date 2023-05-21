package com.nhnacademy.resident.service.household;

import com.nhnacademy.resident.domain.household.HouseholdDto;
import com.nhnacademy.resident.domain.household.RegisterHouseholdDto;

public interface HouseholdService {
    HouseholdDto addHousehold(RegisterHouseholdDto dto);

    void remove(Long householdSerialNumber);
}
