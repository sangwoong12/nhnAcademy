package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import com.nhnacademy.resident.repository.custom.HouseholdMovementAddressRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Modifying
    @Query(value = "UPDATE household_movement_address " +
            "SET last_address_yn = 'N' " +
            "WHERE household_serial_number = :serialNumber " +
            "AND house_movement_report_date = :houseMovementReportDate" , nativeQuery = true)
    void updateLastAddressSetN(@Param("serialNumber") Long serialNumber,@Param("houseMovementReportDate") LocalDate date);

    @Query(value = "SELECT MAX(hma.pk.houseMovementReportDate) FROM HouseholdMovementAddress hma WHERE hma.pk.householdSerialNumber = :serialNumber")
    LocalDate getMaxHouseMovementReportDate(@Param("serialNumber") Long serialNumber);

    HouseholdMovementAddressDto findByPk(HouseholdMovementAddress.Pk pk);

}

