package com.nhnacademy.resident.service.resident;

import com.nhnacademy.resident.config.RootConfig;
import com.nhnacademy.resident.config.WebConfig;
import com.nhnacademy.resident.domain.resident.RegisterResidentDto;
import com.nhnacademy.resident.domain.resident.ResidentDto;
import com.nhnacademy.resident.entity.Resident;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentServiceImplTest {

    @Autowired
    ResidentService residentService;

    @Test
    void addResident() {
        RegisterResidentDto residentDto = new RegisterResidentDto();
        residentDto.setName("홍길동");
        residentDto.setResidentRegistrationNumber("123456-1234567");
        residentDto.setGenderCode(Resident.Gender.M);
        residentDto.setBirthDate(LocalDateTime.of(2000,10,10,10,10,10));
        residentDto.setBirthPlaceCode("서울");
        residentDto.setRegistrationBaseAddress("서울특별시 강남구");
        residentDto.setDeathDate(null);
        residentDto.setDeathPlaceCode(null);
        residentDto.setDeathPlaceAddress(null);

        ResidentDto residentDto1 = residentService.addResident(residentDto);

        Assertions.assertThat(residentDto1.getName()).isEqualTo("홍길동");
    }

    @Test
    void modifyResident() {
    }
}