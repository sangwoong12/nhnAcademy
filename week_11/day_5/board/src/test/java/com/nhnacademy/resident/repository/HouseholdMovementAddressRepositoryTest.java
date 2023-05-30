package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.household_movement_address.HouseholdMovementAddressDto;
import com.nhnacademy.resident.entity.HouseholdMovementAddress;
import com.nhnacademy.resident.utils.HouseholdMovementAddressUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdMovementAddressRepositoryTest {

    @Autowired
    HouseholdMovementAddressRepository repository;

    @Test
    void getMaxHouseMovementReportDate() {
        // given
        Long serialNumber = 1L;

        // when
        LocalDate maxHouseMovementReportDate = repository.getMaxHouseMovementReportDate(serialNumber);

        // then
        Assertions.assertThat(maxHouseMovementReportDate.toString()).isEqualTo("2013-03-05");
    }
    @Test
    void updateLastAddressSetN() {
        // given
        LocalDate lastReportDate = LocalDate.parse("2013-03-05");
        Long serialNumber = 1L;
        //when
        repository.updateLastAddressSetN(serialNumber,lastReportDate);

        // then
        HouseholdMovementAddress.Pk pk = HouseholdMovementAddressUtils.createPk(serialNumber, lastReportDate);
        HouseholdMovementAddress referenceById = repository.getReferenceById(pk);
        Assertions.assertThat(referenceById.getLastAddressYn().getCode()).isEqualTo("N");
    }

    @Test
    void findByPk() {
        // given
        LocalDate lastReportDate = LocalDate.parse("2013-03-05");
        Long serialNumber = 1L;
        HouseholdMovementAddress.Pk pk = HouseholdMovementAddressUtils.createPk(serialNumber, lastReportDate);


        // when
        HouseholdMovementAddressDto byPk = repository.findByPk(pk);

        // then
        Assertions.assertThat(byPk.getPk()).isEqualTo(pk);
    }
}