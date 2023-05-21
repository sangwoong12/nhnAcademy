package com.nhnacademy.resident.repository.custom;

import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {
    void updateLastAddressBySerialNumber(Long serialNumber,LocalDate maxHouseMovementReportDate);
    LocalDate getMaxHouseMovementReportDate(Long serialNumber);
}
