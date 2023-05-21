package com.nhnacademy.resident.utils;

import com.nhnacademy.resident.domain.household_movement_address.RegisterHouseholdMovementAddressDto;
import com.nhnacademy.resident.entity.HouseholdMovementAddress;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class HouseholdMovementAddressUtils {
    private HouseholdMovementAddressUtils() {
    }

    public static HouseholdMovementAddress createHouseholdMovementAddressByDto(Long serialNumber, RegisterHouseholdMovementAddressDto dto) {
        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setLastAddressYn(dto.getLastAddressYn());
        householdMovementAddress.setHouseMovementAddress(dto.getHouseholdMovementAddress());
        householdMovementAddress.setPk(createPk(serialNumber, dto.getHouseMovementReportDate()));
        if(Objects.isNull(dto.getLastAddressYn())){
            householdMovementAddress.setLastAddressYn(HouseholdMovementAddress.LastAddressYn.Y);
        } else {
            householdMovementAddress.setLastAddressYn(dto.getLastAddressYn());
        }
        return householdMovementAddress;
    }

    public static HouseholdMovementAddress.Pk createPk(Long serialNumber, LocalDate houseMovementReportDate) {
        HouseholdMovementAddress.Pk pk = new HouseholdMovementAddress.Pk();
        pk.setHouseholdSerialNumber(serialNumber);
        pk.setHouseMovementReportDate(houseMovementReportDate);
        return pk;
    }
    public static HouseholdMovementAddress.Pk createPk(Long serialNumber, String houseMovementReportDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(houseMovementReportDate, formatter);
        return createPk(serialNumber,localDate);
    }
}
