package com.nhnacademy.resident.domain.resident;

import com.nhnacademy.resident.entity.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter @Setter
public class RegisterResidentDto {
    private String name;

    private String residentRegistrationNumber;

    private Resident.Gender genderCode;

    private LocalDateTime birthDate;

    private String birthPlaceCode;

    private String registrationBaseAddress;

    private LocalDateTime deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;
}
