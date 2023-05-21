package com.nhnacademy.resident.utils;

import com.nhnacademy.resident.domain.household.RegisterHouseholdDto;
import com.nhnacademy.resident.entity.Household;

public class HouseholdUtils {
    private HouseholdUtils(){}
    public static Household createHouseholdByDto(RegisterHouseholdDto dto){
        Household household = new Household();
        household.setHouseholdCompositionDate(dto.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(dto.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(dto.getCurrentHouseMovementAddress());
        return household;
    }
}
