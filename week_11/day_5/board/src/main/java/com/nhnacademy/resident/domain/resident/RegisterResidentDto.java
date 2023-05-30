package com.nhnacademy.resident.domain.resident;

import com.nhnacademy.resident.entity.Resident;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class RegisterResidentDto {
    @NotNull
    private String name;
    @NotNull
    private String residentRegistrationNumber;
    @NotNull
    private Resident.Gender genderCode;
    @NotNull
    private LocalDateTime birthDate;
    @NotNull
    private String birthPlaceCode;
    @NotNull
    private String registrationBaseAddress;

    private LocalDateTime deathDate;

    private String deathPlaceCode;

    private String deathPlaceAddress;
}
