package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.household.HouseholdDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class HouseholdRepositoryTest {

    @Autowired
    HouseholdRepository repository;

    @Test
    void findByHouseholdSerialNumber() {
        // given
        Long serialNumber = 1L;

        // when
        HouseholdDto byHouseholdSerialNumber = repository.findByHouseholdSerialNumber(serialNumber);

        // then
        Assertions.assertThat(byHouseholdSerialNumber.getHouseholdSerialNumber()).isEqualTo(serialNumber);

    }
}