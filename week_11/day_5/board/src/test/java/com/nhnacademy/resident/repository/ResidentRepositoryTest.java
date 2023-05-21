package com.nhnacademy.resident.repository;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.entity.Resident;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentRepositoryTest {
    @Autowired
    ResidentRepository repository;

    @Test
    void save() {
        String name = "홍길동";
        String residentRegistrationNumber = "123456-1234567";
        String gender = "남";

        Resident resident = new Resident();
        resident.setName(name);
        resident.setResidentRegistrationNumber(residentRegistrationNumber);
        resident.setGender(Resident.Gender.valueOf(gender));
        resident.setBirthDate(LocalDateTime.of(2000, 10, 30, 10, 10, 10));
        resident.setBirthPlaceCode("병원");
        resident.setRegistrationBaseAddress("창원시 진해구");

        Resident getResident = repository.saveAndFlush(resident);

        assertThat(getResident.getName()).isEqualTo(name);
        assertThat(getResident.getResidentRegistrationNumber()).isEqualTo(residentRegistrationNumber);
        assertThat(getResident.getGender().getCode()).isEqualTo(gender);
    }
}